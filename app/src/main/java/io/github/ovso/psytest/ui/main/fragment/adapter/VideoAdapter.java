package io.github.ovso.psytest.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import io.github.ovso.psytest.data.network.model.SearchItem;
import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>
    implements VideoAdapterView, VideoAdapterDataModel<SearchItem> {
  private List<SearchItem> items = new ArrayList<>();

  @NonNull @Override
  public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    return VideoViewHolder.create(viewGroup);
  }

  @Override
  public void onBindViewHolder(@NonNull VideoViewHolder viewHolder, int position) {
    if (viewHolder instanceof Bindable) {
      viewHolder.bind(getItem(position));
    }
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

  @Override public SearchItem getItem(int position) {
    return items.get(position);
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