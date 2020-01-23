package io.github.ovso.psytest.ui.video.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.ovso.psytest.R
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.base.ViewHolderProvider
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener
import java.util.ArrayList

class VideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    VideoAdapterView,
    VideoAdapterDataModel<SearchItem> {
  var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null
  private val items = ArrayList<SearchItem>()

  override val size: Int
    get() = items.size

  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder {
    return ViewHolderProvider.create(viewGroup, viewType)
  }

  override fun onBindViewHolder(
    viewHolder: RecyclerView.ViewHolder,
    position: Int
  ) {
    when (viewHolder) {
      is VideoViewHolder -> {
        viewHolder.run {
          bind(getItem(position))
          onItemClickListener = onRecyclerViewItemClickListener
        }
      }
      is AdsViewHolder -> viewHolder.bind(getItem(position))
    }
  }

  override fun getItemViewType(position: Int) =
    if (getItem(position).isEmpty()) R.layout.item_ads_banner else R.layout.item_video

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