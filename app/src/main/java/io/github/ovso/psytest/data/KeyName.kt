package io.github.ovso.psytest.data

enum class KeyName(private val value: String) {
  POSITION("position"),
  PAGE_TOKEN("pageToken"),
  Q("q"),
  MAX_RESULTS("maxResults"),
  ORDER("order"),
  TYPE("type"),
  VIDEO_SYNDICATED("videoSyndicated"),
  KEY("key"),
  PART("part");

  fun get(): String {
    return value
  }
}