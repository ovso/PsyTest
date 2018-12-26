package io.github.ovso.psytest.ui.main.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bumptech.glide.Glide
import io.github.ovso.psytest.R
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener

class VideoViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
    Bindable<SearchItem> {
  private var data: SearchItem? = null
  var onItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null

  override fun bind(item: SearchItem) {
    data = item
    setupPlayButton()
    showThumbnail()
  }

  private fun setupPlayButton() {
    itemView.findViewById<Button>(R.id.play_button)
        .setOnClickListener { v ->
          onItemClickListener?.onItemClick(
              v, data!!, 0
          )
        }
  }

  private fun showThumbnail() {
    Glide.with(itemView.context)
        .load(data!!.snippet!!.thumbnails!!.medium!!.url)
        .into(itemView.findViewById(R.id.imageview_video_item))
  }

  companion object {

    fun create(parent: ViewGroup): VideoViewHolder {
      return VideoViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
      )
    }
  }
}
