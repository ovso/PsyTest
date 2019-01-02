package io.github.ovso.psytest.ui.main.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener
import java.util.ArrayList

class VideoAdapter : RecyclerView.Adapter<VideoViewHolder>(),
    VideoAdapterView,
    VideoAdapterDataModel<SearchItem> {
  var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null
  private val items = ArrayList<SearchItem>()

  override val size: Int
    get() = items.size

  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    viewType: Int
  ): VideoViewHolder {
    return VideoViewHolder.create(viewGroup)
  }

  override fun onBindViewHolder(
    viewHolder: VideoViewHolder,
    position: Int
  ) {
    viewHolder.bind(getItem(position))
    viewHolder.onItemClickListener = onRecyclerViewItemClickListener
  }

  override fun getItemCount(): Int {
    return size
  }

  override fun getItem(position: Int): SearchItem {
    return items[position]
  }

  override fun clear() {
    items.clear()
  }

  override fun refresh() {
    notifyDataSetChanged()
  }

  override fun refresh(
    positionStart: Int,
    itemCount: Int
  ) {
    notifyItemRangeInserted(positionStart, itemCount)
  }

  override fun addAll(items: List<SearchItem>?) {
    this.items.addAll(items!!)
  }
}