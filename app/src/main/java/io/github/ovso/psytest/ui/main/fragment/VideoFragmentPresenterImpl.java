package io.github.ovso.psytest.ui.main.fragment;

import android.os.Bundle;
import timber.log.Timber;

public class VideoFragmentPresenterImpl implements VideoFragmentPresenter {

  private VideoFragmentPresenter.View view;

  public VideoFragmentPresenterImpl(VideoFragmentPresenter.View $view) {
    view = $view;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    Timber.d("onActivityCreated");
  }
}
