package io.github.ovso.psytest.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.ui.base.view.BaseFragment;
import javax.inject.Inject;

public class VideoFragment extends BaseFragment implements VideoFragmentPresenter.View {
  @Inject VideoFragmentPresenter presenter;

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
}
