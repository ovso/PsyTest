package io.github.ovso.psytest.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import io.github.ovso.psytest.data.network.model.SearchItem;
import io.github.ovso.psytest.ui.base.interfaces.OnRecyclerViewItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>
    implements VideoAdapterView, VideoAdapterDataModel<SearchItem> {
  @Setter protected OnRecyclerViewItemClickListener<SearchItem> onRecyclerViewItemClickListener;
  private List<SearchItem> items = new ArrayList<>();

  @NonNull @Override
  public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    return VideoViewHolder.Companion.create(viewGroup);
  }

  @Override
  public void onBindViewHolder(@NonNull VideoViewHolder viewHolder, int position) {
    viewHolder.bind(getItem(position));
    viewHolder.setOnItemClickListener(onRecyclerViewItemClickListener);
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public SearchItem getItem(int position) {
    return items.get(position);
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }

  @Override public void refresh(int positionStart, int itemCount) {
    notifyItemRangeInserted(positionStart, itemCount);
  }

  @Override public void addAll(@Nullable List<? extends SearchItem> $items) {
    if ($items != null) {
      items.addAll($items);
    }
  }
}