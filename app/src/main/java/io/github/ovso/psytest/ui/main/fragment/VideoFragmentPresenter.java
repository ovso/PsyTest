package io.github.ovso.psytest.ui.main.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import io.github.ovso.psytest.data.network.model.SearchItem;

public interface VideoFragmentPresenter {

  void onActivityCreated(Bundle args);

  void onDestroyView();

  void onItemClick(SearchItem data);

  void onLoadMore();

  void onRefresh();

  interface View {

    void setupRecyclerView();

    void refresh();

    void showVideoTypeDialog(DialogInterface.OnClickListener onClickListener);

    void showPortraitVideo(String videoId);

    void showLandscapeVideo(String videoId);

    void showYoutubeUseWarningDialog();

    void setLoaded();

    void setupSwipeRefresh();

    void hideRefresh();
  }
}
