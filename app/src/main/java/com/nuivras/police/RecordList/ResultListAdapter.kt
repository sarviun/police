package com.nuivras.police.RecordList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.nuivras.police.StreetLevelCrime
import com.nuivras.police.databinding.IncidentListViewItemBinding

class ResultListAdapter (val onClickListener: OnClickListener) :
    ListAdapter<Any, ResultListAdapter.BaseViewHolder<*>>(DiffCallback) {

    class OnClickListener(val clickLocationListener: (crime: StreetLevelCrime, view: View) -> Unit) {
        fun onIncidentClick(crime: StreetLevelCrime, view: View) = clickLocationListener(crime, view)
    }

    abstract class BaseViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }

    class IncidentViewHolder(private val binding: IncidentListViewItemBinding) :
        BaseViewHolder<StreetLevelCrime>(binding) {
        override fun bind(item: StreetLevelCrime) {
            binding.textView.text = item.category
            binding.textView3.text = item.location?.street?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return IncidentViewHolder(IncidentListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (getItem(position) is StreetLevelCrime) {
            (holder as IncidentViewHolder).bind(getItem(position) as StreetLevelCrime)
            holder.itemView.setOnClickListener {
                onClickListener.onIncidentClick(getItem(position) as StreetLevelCrime, holder.itemView)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return if (oldItem is StreetLevelCrime && newItem is StreetLevelCrime)
                oldItem.id == newItem.id
            else false
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return if (oldItem is StreetLevelCrime && newItem is StreetLevelCrime)
                oldItem == newItem
            else false
        }
    }

}