package io.github.ovso.psytest.ui.video.adapter

interface VideoAdapterView {
  fun refresh()

  fun refresh(
    positionStart: Int,
    itemCount: Int
  )
}