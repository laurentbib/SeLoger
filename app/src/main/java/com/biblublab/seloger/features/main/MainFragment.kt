package com.biblublab.seloger.features.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.biblublab.seloger.R
import com.biblublab.seloger.common.extension.*
import com.biblublab.seloger.common.mvi.BaseFragment
import com.biblublab.seloger.common.ui.UiState
import com.biblublab.seloger.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainScreenState, MainScreenEffect, MainScreenAction, MainViewModel>(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)
    override val viewModel: MainViewModel by viewModel()

    private val mainAdapter by lazy {
        MainAdapter {binding, realProperty ->
            viewModel.process(MainScreenAction.OnClickItem(binding, realProperty))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        waitForTransition(binding.realPropertyListView)
    }

    override fun initUI() {
        binding.realPropertyListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
    }

    override fun renderViewState(viewState: MainScreenState) {
        when(viewState.uiState){
            is UiState.Error -> {
                binding.loadingShimmer.endShimmerAnim()
                activity?.showSnackbar(viewState.uiState.mess)
            }
            UiState.Fetched -> {
                binding.loadingShimmer.endShimmerAnim()
                binding.realPropertyListView.visible(true)
            }
            UiState.Fetching -> {
                binding.loadingShimmer.beginShimmerAnim()
                binding.realPropertyListView.visible(false)
            }
            UiState.NotFetched -> viewModel.process(MainScreenAction.FetchData)
        }
        mainAdapter.submitList(viewState.realPropertyList)
    }

    override fun renderViewEffect(viewEffect: MainScreenEffect) {
        when(viewEffect){
            is MainScreenEffect.OpenDetail -> {
                val extras = FragmentNavigatorExtras(viewEffect.binding.realPropertyImg to viewEffect.binding.realPropertyImg.transitionName,
                viewEffect.binding.realPropertyInfoBackground to viewEffect.binding.realPropertyInfoBackground.transitionName,
                viewEffect.binding.realPropertyPrice to viewEffect.binding.realPropertyPrice.transitionName,
                viewEffect.binding.realPropertyType to viewEffect.binding.realPropertyType.transitionName,
                viewEffect.binding.realPropertyTypeOtherInfos to viewEffect.binding.realPropertyTypeOtherInfos.transitionName,
                viewEffect.binding.realPropertyPricePrefix to viewEffect.binding.realPropertyPricePrefix.transitionName,
                viewEffect.binding.realPropertyPriceContainer to viewEffect.binding.realPropertyPriceContainer.transitionName,
                )
                findNavController().navigate(MainFragmentDirections.openDetailFragment(viewEffect.realPropertyEntity), extras)
            }
        }
    }
}