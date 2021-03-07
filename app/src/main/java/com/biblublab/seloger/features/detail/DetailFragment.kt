package com.biblublab.seloger.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.biblublab.seloger.R
import com.biblublab.seloger.common.extension.loadImg
import com.biblublab.seloger.common.extension.showSnackbar
import com.biblublab.seloger.common.extension.viewBinding
import com.biblublab.seloger.common.mvi.BaseFragment
import com.biblublab.seloger.common.ui.UiState
import com.biblublab.seloger.databinding.DetailFragmentBinding
import com.biblublab.seloger.features.RealPropertyEntity
import com.google.android.material.appbar.AppBarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Math.abs


class DetailFragment : BaseFragment<DetailScreenState, DetailScreenEffect, DetailScreenAction, DetailViewModel>(R.layout.detail_fragment) {

    private val binding by viewBinding(DetailFragmentBinding::bind)
    override val viewModel: DetailViewModel by viewModel()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition =
            TransitionInflater.from(this.context).inflateTransition(R.transition.shared_transition)
        sharedElementReturnTransition =
            TransitionInflater.from(this.context).inflateTransition(R.transition.shared_transition)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTransitionNames()
        postponeEnterTransition()
    }

    private fun setTransitionNames() {
        binding.realPropertyType.transitionName =
            getString(R.string.transition_type, args.realPropertyEntity.id)
        binding.realPropertyOtherInfos.transitionName =
            getString(R.string.transition_other_infos, args.realPropertyEntity.id)
        binding.realPropertyPrice.transitionName =
            getString(R.string.transition_price, args.realPropertyEntity.id)
        binding.realPropertyImg.transitionName =
            getString(R.string.transition_img, args.realPropertyEntity.id)
        binding.realPropertyInfoBackground.transitionName =
        getString(R.string.transition_background, args.realPropertyEntity.id)
        binding.realPropertyPriceContainer.transitionName =
                getString(R.string.transition_price_container, args.realPropertyEntity.id)
        binding.realPropertyPricePrefix.transitionName =
                getString(R.string.transition_price_prefix, args.realPropertyEntity.id)
    }

    override fun initUI() {
        binding.appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                val scrollRange = appBarLayout.totalScrollRange
                val percentScrolled = abs(verticalOffset / scrollRange.toFloat())

                binding.realPropertyToolbar.alpha = percentScrolled
                binding.sunnyIcon.alpha = 1 - percentScrolled
            }
        )
        binding.realPropertyToolbar.title = args.realPropertyEntity.professionalLabel
    }

    override fun renderViewState(viewState: DetailScreenState) {
        when (viewState.uiState) {
            is UiState.Error -> {
                activity?.showSnackbar(viewState.uiState.mess)
                bindData(args.realPropertyEntity)
            }
            UiState.Fetched -> {
                bindData(viewState.realPropertyEntity!!)
            }
            UiState.Fetching -> {
                    //do nothing
            }
            UiState.NotFetched -> viewModel.process(DetailScreenAction.GetDetail(args.realPropertyEntity.id))
        }
    }

    override fun renderViewEffect(viewEffect: DetailScreenEffect) {}

    private fun bindData(realPropertyEntity: RealPropertyEntity) {
        with(realPropertyEntity) {
            url?.let { binding.realPropertyImg.loadImg(it) }
            binding.realPropertyPrice.text = price
            binding.realPropertyOtherInfos.text = otherInfosLabel
            binding.realPropertyRoomInfos.text = bedRoomsInfosLabel
            binding.realPropertyType.text = type
        }
        startPostponedEnterTransition()
    }
}