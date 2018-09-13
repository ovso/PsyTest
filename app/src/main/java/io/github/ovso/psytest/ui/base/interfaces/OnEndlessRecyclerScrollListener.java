package io.github.ovso.psytest.ui.base.interfaces;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import lombok.Setter;
import lombok.experimental.Accessors;

public class OnEndlessRecyclerScrollListener extends RecyclerView.OnScrollListener {

  private int visibleThreshold = 1;
  private int lastVisibleItem, totalItemCount;
  private boolean loading;
  private LinearLayoutManager linearLayoutManager;
  private OnLoadMoreListener onLoadMoreListener;

  OnEndlessRecyclerScrollListener(OnEndlessRecyclerScrollListener.Builder builder) {
    linearLayoutManager = builder.linearLayoutManager;
    onLoadMoreListener = builder.onLoadMoreListener;
  }

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    totalItemCount = linearLayoutManager.getItemCount();
    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
      loading = true;
      if (onLoadMoreListener != null) {
        onLoadMoreListener.onLoadMore();
      }
    }
  }

  public void setLoaded() {
    loading = false;
  }

  public interface OnLoadMoreListener {
    void onLoadMore();
  }

  public static class Builder
      implements io.github.ovso.psytest.ui.base.Builder<OnEndlessRecyclerScrollListener> {
    @Setter @Accessors(chain = true) private LinearLayoutManager linearLayoutManager;
    @Setter @Accessors(chain = true) private OnLoadMoreListener onLoadMoreListener;

    @Override public OnEndlessRecyclerScrollListener build() {
      return new OnEndlessRecyclerScrollListener(this);
    }
  }
}
