package io.github.ovso.psytest.ui.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import io.github.ovso.psytest.R
import io.github.ovso.psytest.ui.video.adapter.AdsViewHolder
import io.github.ovso.psytest.ui.video.adapter.VideoViewHolder

class ViewHolderProvider {

  companion object {
    fun create(parent:ViewGroup, @LayoutRes layoutId:Int): RecyclerView.ViewHolder{
      return if(layoutId == R.layout.item_video) {
        VideoViewHolder.create(parent)
      } else {
        AdsViewHolder.create(parent)
      }
    }
  }
}