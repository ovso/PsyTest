package io.github.ovso.psytest.ui.main.adapter

import android.content.Intent
import android.view.View
import io.github.ovso.psytest.ui.video.VideoActivity

data class MainItem(
  val title: String = "",
  val query: String = ""
) {

  fun onClick(v: View) {
    val ctx = v.context
    ctx.startActivity(
        Intent(ctx, VideoActivity::class.java).apply {
          putExtra("title", title)
          putExtra("query", query)
        }
    )
  }
}