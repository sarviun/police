package com.nuivras.police

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.nuivras.police.databinding.IncidentListViewItemBinding

class ResultListAdapter (private val dataset: List<Any>) : RecyclerView.Adapter<ResultListAdapter.BaseViewHolder<*>>() {

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
        (holder as IncidentViewHolder).bind(dataset.get(position) as StreetLevelCrime)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}