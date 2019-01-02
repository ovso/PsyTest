package io.github.ovso.psytest.ui.main.fragment.adapter

interface VideoAdapterView {
  fun refresh()

  fun refresh(
    positionStart: Int,
    itemCount: Int
  )
}