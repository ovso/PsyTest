package io.github.ovso.psytest.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.ovso.psytest.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startActivity(Intent(this, MainActivity::class.java))
    finish()
  }
}
