package io.github.ovso.psytest.ui.base.interfaces

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.github.ovso.psytest.ui.base.IBuilder

class OnEndlessRecyclerScrollListener private constructor(builder: OnEndlessRecyclerScrollListener.Builder) :
    RecyclerView.OnScrollListener() {

  private var visibleThreshold = 1
  private var lastVisibleItem: Int = 0
  private var totalItemCount: Int = 0
  private var loading: Boolean = false
  private val linearLayoutManager: LinearLayoutManager?
  private val onLoadMoreListener: OnLoadMoreListener?

  init {
    linearLayoutManager = builder.linearLayoutManager
    onLoadMoreListener = builder.onLoadMoreListener
    visibleThreshold = builder.visibleThreshold
  }

  override fun onScrolled(
    recyclerView: RecyclerView,
    dx: Int,
    dy: Int
  ) {
    super.onScrolled(recyclerView, dx, dy)

    totalItemCount = linearLayoutManager!!.itemCount
    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
    if (!loading && totalItemCount <= lastVisibleItem + visibleThreshold) {
      loading = true
      onLoadMoreListener?.onLoadMore()
    }
  }

  fun setLoaded() {
    loading = false
  }

  interface OnLoadMoreListener {
    fun onLoadMore()
  }

  class Builder : IBuilder<OnEndlessRecyclerScrollListener> {
    var visibleThreshold = 1
    var linearLayoutManager: LinearLayoutManager? = null
    var onLoadMoreListener: OnLoadMoreListener? = null
    fun setLayoutManager(layoutManager: LinearLayoutManager): Builder {
      this.linearLayoutManager = layoutManager
      return this
    }

    fun setVisibleThreshold(visibleThreshold: Int): Builder {
      this.visibleThreshold = visibleThreshold
      return this
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener): Builder {
      this.onLoadMoreListener = onLoadMoreListener
      return this
    }

    override fun build(): OnEndlessRecyclerScrollListener {
      return OnEndlessRecyclerScrollListener(this)
    }
  }
}