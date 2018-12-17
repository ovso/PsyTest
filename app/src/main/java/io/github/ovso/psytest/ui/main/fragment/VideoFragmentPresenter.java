package io.github.ovso.psytest.ui.main.fragment;

import android.os.Bundle;
import io.github.ovso.psytest.data.network.model.SearchItem;

public interface VideoFragmentPresenter {

  void onActivityCreated(Bundle args);

  void onDestroyView();

  void onItemClick(SearchItem data);

  void onLoadMore();

  void onRefresh();

  boolean onOptionsItemSelected(int itemId);

  interface View {

    void setupRecyclerView();

    void refresh();

    void showVideo(String videoId);

    void showYoutubeUseWarningDialog();

    void setLoaded();

    void setupSwipeRefresh();

    void hideLoading();

    void showLoading();

    void setupAdListener();

    void navigateToWeb(String url);
  }
}
