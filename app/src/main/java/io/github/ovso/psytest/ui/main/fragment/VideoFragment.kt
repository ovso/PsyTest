package io.github.ovso.psytest.ui.main.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.android.gms.ads.AdListener
import io.github.ovso.psytest.R
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.ui.base.interfaces.OnEndlessRecyclerScrollListener
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener
import io.github.ovso.psytest.ui.base.view.BaseFragment
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapter
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterView
import io.github.ovso.psytest.ui.video.VideoActivity
import io.github.ovso.psytest.ui.web.WebActivity
import kotlinx.android.synthetic.main.fragment_video.recyclerview_video
import kotlinx.android.synthetic.main.fragment_video.swiperefreshlayout_video
import java.util.Objects
import javax.inject.Inject

class VideoFragment : BaseFragment(),
    VideoFragmentPresenter.View,
    OnRecyclerViewItemClickListener<SearchItem>,
    OnEndlessRecyclerScrollListener.OnLoadMoreListener {

  var presenter: VideoFragmentPresenter? = null
    @Inject set
  var adapter: VideoAdapter? = null
    @Inject set
  var adapterView: VideoAdapterView? = null
    @Inject set

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    setHasOptionsMenu(true)
  }

  override fun getLayoutResID(): Int {
    return R.layout.fragment_video
  }

  override fun onActivityCreate(savedInstanceState: Bundle?) {
    presenter!!.onActivityCreated(Objects.requireNonNull<Bundle>(arguments))
  }

  override fun onDestroyView() {
    presenter!!.onDestroyView()
    super.onDestroyView()
  }

  override fun setupRecyclerView() {
    recyclerview_video.layoutManager = LinearLayoutManager(context)
    recyclerview_video.adapter = adapter
    recyclerview_video.setOnItemClickListener(this)
    recyclerview_video.addOnScrollListener(
        OnEndlessRecyclerScrollListener.Builder()
            .setLayoutManager(
                recyclerview_video.layoutManager as LinearLayoutManager
            ).setOnLoadMoreListener(this)
            .setVisibleThreshold(5).build()
    )
  }

  override fun refresh() {
    adapterView?.refresh()
  }

  override fun showVideo(videoId: String?) {
    val intent = Intent(context, VideoActivity::class.java)
    intent.putExtra("video_id", videoId)
    startActivity(intent)
  }

  override fun showYoutubeUseWarningDialog() {
    AlertDialog.Builder(activity)
        .setMessage(R.string.youtube_use_warning)
        .setPositiveButton(android.R.string.ok, null)
        .show()
  }

  override fun setLoaded() {
    recyclerview_video.onEndlessRecyclerScrollListener.setLoaded()
  }

  override fun setupSwipeRefresh() {
    swiperefreshlayout_video.setOnRefreshListener {
      if (interstitialAd.isLoaded) {
        interstitialAd.show()
      } else {
        presenter!!.onRefresh()
      }
    }
  }

  override fun hideLoading() {
    swiperefreshlayout_video.isRefreshing = false
  }

  override fun showLoading() {
    swiperefreshlayout_video.isRefreshing = true
  }

  override fun setupAdListener() {
    interstitialAd.adListener = object : AdListener() {
      override fun onAdClosed() {
        super.onAdClosed()
        presenter!!.onRefresh()
      }
    }
  }

  override fun navigateToWeb(
    url: String,
    title: String
  ) {
    val intent = Intent(context, WebActivity::class.java)
    intent.putExtra("url", url)
    intent.putExtra("title", title)
    startActivity(intent)
  }

  override fun onLoadMore() {
    presenter!!.onLoadMore()
  }

  override fun onCreateOptionsMenu(
    menu: Menu?,
    inflater: MenuInflater?
  ) {
    inflater!!.inflate(R.menu.menu_main, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return presenter!!.onOptionsItemSelected(item.itemId)
  }

  override fun onItemClick(data: SearchItem) {
    presenter!!.onItemClick(data)
  }

  companion object {

    fun newInstance(args: Bundle): Fragment {
      val f = VideoFragment()
      f.arguments = args
      return f
    }
  }
}