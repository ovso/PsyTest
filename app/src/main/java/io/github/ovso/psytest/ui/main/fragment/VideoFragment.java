package io.github.ovso.psytest.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.ui.base.view.BaseFragment;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapter;
import io.github.ovso.psytest.ui.main.fragment.adapter.VideoAdapterView;
import javax.inject.Inject;

public class VideoFragment extends BaseFragment implements VideoFragmentPresenter.View {

  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @Inject VideoFragmentPresenter presenter;
  @Inject VideoAdapter adapter;
  @Inject VideoAdapterView adapterView;

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
  }

  @Override public void refresh() {
    adapterView.refresh();
  }
}