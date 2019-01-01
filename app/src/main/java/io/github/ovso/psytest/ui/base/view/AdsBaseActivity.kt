package io.github.ovso.psytest.ui.base.view

import android.os.Bundle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd

abstract class AdsBaseActivity : BaseActivity() {
  internal var interstitialAd: InterstitialAd? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    interstitialAd = MyAdView.getAdmobInterstitialAd(applicationContext)
    interstitialAd!!.adListener = object : AdListener() {
      override fun onAdClosed() {
        super.onAdClosed()
        finish()
      }
    }
  }

  override fun onBackPressed() {
    if (interstitialAd!!.isLoaded) {
      interstitialAd!!.show()
    } else {
      finish()
    }
  }
}
