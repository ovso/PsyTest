package io.github.ovso.psytest.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import io.github.ovso.psytest.R;
import io.github.ovso.psytest.data.network.model.SearchItem;

public class VideoViewHolder extends RecyclerView.ViewHolder implements Bindable<SearchItem> {
  @BindView(R.id.thumbnail_image_view) ImageView thumbnailImageView;

  private VideoViewHolder(@NonNull View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public static VideoViewHolder create(ViewGroup parent) {
    return new VideoViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false));
  }

  @Override public void bind(SearchItem data) {
    Glide.with(itemView.getContext())
        .load(data.getSnippet().getThumbnails().getMedium().getUrl())
        .into(thumbnailImageView);
  }
}
