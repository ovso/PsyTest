package io.github.ovso.psytest.ui.main.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import com.google.android.gms.ads.AdListener;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.data.network.model.SearchItem;
import io.github.ovso.psytest.ui.base.interfaces.OnEndlessRecyclerScrollListener;
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener;
import io.github.ovso.psytest.ui.base.view.BaseFragment;
import io.github.ovso.psytest.ui.base.view.VideoRecyclerView;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapter;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterView;
import io.github.ovso.psytest.ui.video.VideoActivity;
import io.github.ovso.psytest.ui.web.WebActivity;
import javax.inject.Inject;

public class VideoFragment extends BaseFragment implements VideoFragmentPresenter.View,
    OnRecyclerViewItemClickListener<SearchItem>,
    OnEndlessRecyclerScrollListener.OnLoadMoreListener {

  @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.recycler_view) VideoRecyclerView recyclerView;
  @Inject VideoFragmentPresenter presenter;
  @Inject VideoAdapter adapter;
  @Inject VideoAdapterView adapterView;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    setHasOptionsMenu(true);
  }

  public static Fragment newInstance(Bundle args) {
    VideoFragment f = new VideoFragment();
    f.setArguments(args);
    return f;
  }

  @Override protected int getLayoutResID() {
    return R.layout.fragment_video;
  }

  @Override protected void onActivityCreate(Bundle savedInstanceState) {
    presenter.onActivityCreated(getArguments());
  }

  @Override public void onDestroyView() {
    presenter.onDestroyView();
    super.onDestroyView();
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);
    recyclerView.setOnItemClickListener(this);
    recyclerView.addOnScrollListener(
        new OnEndlessRecyclerScrollListener
            .Builder((LinearLayoutManager) recyclerView.getLayoutManager(), this)
            .setVisibleThreshold(20)
            .build()
    );
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void showVideo(String videoId) {
    Intent intent = new Intent(getContext(), VideoActivity.class);
    intent.putExtra("video_id", videoId);
    startActivity(intent);
  }

  @Override public void showYoutubeUseWarningDialog() {
    new AlertDialog.Builder(getActivity()).setMessage(R.string.youtube_use_warning)
        .setPositiveButton(android.R.string.ok, null)
        .show();
  }

  @Override public void setLoaded() {
    recyclerView.getOnEndlessRecyclerScrollListener().setLoaded();
  }

  @Override public void setupSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      if (interstitialAd.isLoaded()) {
        interstitialAd.show();
      } else {
        presenter.onRefresh();
      }
    });
  }

  @Override public void hideLoading() {
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showLoading() {
    swipeRefreshLayout.setRefreshing(true);
  }

  @Override public void setupAdListener() {
    interstitialAd.setAdListener(new AdListener() {
      @Override public void onAdClosed() {
        super.onAdClosed();
        presenter.onRefresh();
      }
    });
  }

  @Override public void navigateToWeb(String url, String title) {
    Intent intent = new Intent(getContext(), WebActivity.class);
    intent.putExtra("url", url);
    intent.putExtra("title", title);
    startActivity(intent);
  }

  @Override public void onLoadMore() {
    presenter.onLoadMore();
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_main, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return presenter.onOptionsItemSelected(item.getItemId());
  }

  @Override public void onItemClick(SearchItem data) {
    presenter.onItemClick(data);
  }
}