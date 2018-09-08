package io.github.ovso.psytest.data.network;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor public enum ApiEndPoint {
  CARS("http://recruit.heydealer.com/");

  private String url;
}