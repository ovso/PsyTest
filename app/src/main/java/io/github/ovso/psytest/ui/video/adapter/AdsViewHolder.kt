package io.github.ovso.psytest.ui.video.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import io.github.ovso.psytest.R
import io.github.ovso.psytest.data.network.model.SearchItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_ads_banner.all_ads_banner

class AdsViewHolder private constructor(
  override val containerView: View?
) : RecyclerView.ViewHolder(containerView!!),
    Bindable<SearchItem>, LayoutContainer {

  override fun bind(item: SearchItem) {
    all_ads_banner.loadAd(AdRequest.Builder().build())
  }

  companion object {

    fun create(parent: ViewGroup): AdsViewHolder {
      return AdsViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_ads_banner, parent, false)
      )
    }
  }
}