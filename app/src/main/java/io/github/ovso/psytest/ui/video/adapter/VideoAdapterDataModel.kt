package io.github.ovso.psytest.ui.video.adapter

interface VideoAdapterDataModel<T> {
  val size: Int

  fun addAll(items: List<T>?)

  fun getItem(position: Int): T

  fun clear()
}
