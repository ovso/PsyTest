package io.github.ovso.psytest.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import io.github.ovso.psytest.data.network.model.SearchItem;
import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter
    implements VideoAdapterView, VideoAdapterDataModel<SearchItem> {
  private List<SearchItem> items = new ArrayList<>();

  @NonNull @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return null;
  }

  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void addAll(List<SearchItem> $items) {
    items.addAll($items);
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public void refresh() {
    notifyItemRangeInserted(0, getSize());
  }

  @Override public void refresh(int positionStart, int itemCount) {
    notifyItemRangeInserted(positionStart, itemCount);
  }
}
