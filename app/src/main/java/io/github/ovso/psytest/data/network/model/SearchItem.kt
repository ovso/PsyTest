package io.github.ovso.psytest.data.network.model

class SearchItem {
  val kind: String? = null
  val etag: String? = null
  val id: SearchItemId? = null
  val snippet: Snippet? = null

  fun isEmpty() = id?.videoId.isNullOrEmpty()
}