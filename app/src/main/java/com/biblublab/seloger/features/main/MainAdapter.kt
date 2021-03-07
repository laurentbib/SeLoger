package com.biblublab.seloger.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.biblublab.seloger.R
import com.biblublab.seloger.common.extension.loadImg
import com.biblublab.seloger.databinding.MainItemViewBinding
import com.biblublab.seloger.features.RealPropertyEntity

class MainAdapter(private val onClickItem : (MainItemViewBinding, RealPropertyEntity) -> Unit) : ListAdapter<RealPropertyEntity, MainAdapter.MainViewHolder>(
    RealPropertyItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) =  holder.bind(getItem(position))

    inner class MainViewHolder(private val binding : MainItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(realPropertyEntity: RealPropertyEntity){
            setupTransition(binding, realPropertyEntity.id)
            realPropertyEntity.url?.let {binding.realPropertyImg.loadImg(it)  }
            binding.realPropertyPrice.text = realPropertyEntity.price
            binding.realPropertyType.text = realPropertyEntity.type
            binding.realPropertyTypeOtherInfos.text = realPropertyEntity.otherInfosLabel
            binding.root.setOnClickListener { onClickItem(binding, realPropertyEntity) }
        }

        private fun setupTransition(binding : MainItemViewBinding, id : Int) = with(binding){
            val context = binding.root.context
            realPropertyType.transitionName = context.getString(R.string.transition_type, id)
            realPropertyTypeOtherInfos.transitionName = context.getString(R.string.transition_other_infos, id)
            realPropertyPrice.transitionName = context.getString(R.string.transition_price, id)
            realPropertyImg.transitionName = context.getString(R.string.transition_img, id)
            realPropertyInfoBackground.transitionName = context.getString(R.string.transition_background, id)
            realPropertyPriceContainer.transitionName = context.getString(R.string.transition_price_container, id)
            realPropertyPricePrefix.transitionName = context.getString(R.string.transition_price_prefix, id)
        }
    }
}

internal  class RealPropertyItemDiffCallback : DiffUtil.ItemCallback<RealPropertyEntity>(){
    override fun areItemsTheSame(oldItem: RealPropertyEntity, newItem: RealPropertyEntity): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: RealPropertyEntity, newItem: RealPropertyEntity): Boolean = oldItem == newItem
}
