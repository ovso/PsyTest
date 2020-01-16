package io.github.ovso.psytest.ui.main.fragment

import android.os.Bundle
import io.github.ovso.psytest.data.network.model.SearchItem

interface VideoFragmentPresenter {

  fun onActivityCreated(args: Bundle)

  fun onDestroyView()

  fun onItemClick(data: SearchItem)

  fun onLoadMore()

  fun onRefresh()

  fun onOptionsItemSelected(itemId: Int): Boolean

  interface View {

    fun setupRecyclerView()

    fun refresh()

    fun showVideo(videoId: String?)

    fun showYoutubeUseWarningDialog()

    fun setLoaded()

    fun setupSwipeRefresh()

    fun hideLoading()

    fun showLoading()

    fun setupAdListener()

    fun navigateToWeb(
      url: String,
      title: String
    )

    fun addEvent()
  }
}
