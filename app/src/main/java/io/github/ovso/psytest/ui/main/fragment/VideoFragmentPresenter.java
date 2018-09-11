package io.github.ovso.psytest.ui.main.fragment;

import android.os.Bundle;

public interface VideoFragmentPresenter {

  void onActivityCreated(Bundle args);

  void onDestroyView();

  interface View {

    void setupRecyclerView();

    void refresh();
  }
}
