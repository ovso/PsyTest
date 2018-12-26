package io.github.ovso.psytest.ui.main.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.ovso.psytest.R
import io.github.ovso.psytest.R.id.play_button
import io.github.ovso.psytest.R.id.thumbnail_image_view
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener

class VideoViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
    Bindable<SearchItem> {
  private var data: SearchItem? = null
  var onItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null

  override fun bind(item: SearchItem) {
    data = item
    showThumbnail();
    setupPlayButton();
  }

  private fun setupPlayButton() {
    (play_button as Button).setOnClickListener { v ->
      onItemClickListener?.onItemClick(
          v, data!!, 0
      )
    }
  }

  private fun showThumbnail() {
    Glide.with(itemView.context)
        .load(data!!.snippet!!.thumbnails!!.medium!!.url)
        .into(thumbnail_image_view as ImageView)
  }

  companion object {

    fun create(parent: ViewGroup): VideoViewHolder {
      return VideoViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
      )
    }
  }
}
