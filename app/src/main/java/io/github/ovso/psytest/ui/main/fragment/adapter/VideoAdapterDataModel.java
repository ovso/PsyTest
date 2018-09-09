package io.github.ovso.psytest.ui.main.fragment.adapter;

import java.util.List;

public interface VideoAdapterDataModel<T> {
  int getSize();

  void addAll(List<T> items);

  void clear();
}
