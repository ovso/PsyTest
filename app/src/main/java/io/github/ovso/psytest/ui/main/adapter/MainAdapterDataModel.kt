package io.github.ovso.psytest.ui.main.adapter

interface MainAdapterDataModel {
  val size: Int
  fun createItems(size: Int)
}