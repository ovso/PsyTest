package io.github.ovso.psytest.data.network.model;

import lombok.Getter;

@Getter public class SearchThumbnails {
  private ThumbnailsInfo defaults;
  private ThumbnailsInfo medium;
  private ThumbnailsInfo high;
}