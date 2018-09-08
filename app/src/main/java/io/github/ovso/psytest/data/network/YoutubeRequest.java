package io.github.ovso.psytest.data.network;

import okhttp3.Headers;

public class YoutubeRequest extends BaseRequest<YoutubeService> {
  @Override protected Class<YoutubeService> getApiClass() {
    return YoutubeService.class;
  }

  @Override protected Headers createHeaders() {
    return new Headers.Builder().build();
  }

  @Override protected String getBaseUrl() {
    return null;
  }
}
