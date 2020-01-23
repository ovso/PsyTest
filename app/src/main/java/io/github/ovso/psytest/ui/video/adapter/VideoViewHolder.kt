package io.github.ovso.psytest.ui.video.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.github.ovso.psytest.R
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_video.imageview_video_item
import kotlinx.android.synthetic.main.item_video.play_button

class VideoViewHolder private constructor(
  override val containerView: View?
) : RecyclerView.ViewHolder(containerView!!),
    Bindable<SearchItem>, LayoutContainer {
  private var data: SearchItem? = null
  var onItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null

  override fun bind(item: SearchItem) {
    data = item
    play_button.setOnClickListener { onItemClickListener?.onItemClick(data!!) }
    Glide.with(itemView.context)
        .load(data!!.snippet!!.thumbnails!!.medium!!.url)
        .into(imageview_video_item)
  }

  companion object {

    fun create(parent: ViewGroup): VideoViewHolder {
      return VideoViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
      )
    }
  }
}