package io.github.ovso.psytest.ui.base.interfaces;

import android.view.View;

public interface OnRecyclerViewItemClickListener<T> {
  void onItemClick(View view, T data, int itemPosition);
}