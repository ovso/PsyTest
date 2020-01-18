package io.github.ovso.psytest.ui.main.rvadapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import io.github.ovso.psytest.R

class MainRvAdapter : ListAdapter<MainItem, MainViewHolder>(MainDiffUtil()) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MainViewHolder {
    return MainViewHolder(
        parent, viewType
    )
  }

  override fun getItemViewType(position: Int): Int {
    return R.layout.item_main
  }

  override fun onBindViewHolder(
    holder: MainViewHolder,
    position: Int
  ) {
    holder.onBindViewHolder(getItem(position))
  }

}

private class MainDiffUtil : DiffUtil.ItemCallback<MainItem>() {
  override fun areItemsTheSame(
    oldItem: MainItem,
    newImte: MainItem
  ): Boolean {
    return oldItem == newImte
  }

  override fun areContentsTheSame(
    oldItem: MainItem,
    newItem: MainItem
  ): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }

}