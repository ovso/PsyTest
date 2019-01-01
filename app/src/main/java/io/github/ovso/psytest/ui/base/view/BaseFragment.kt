package io.github.ovso.psytest.ui.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.InterstitialAd
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {
  protected var interstitialAd: InterstitialAd? = null

  protected abstract val layoutResID: Int

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(layoutResID, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState
    )
    setHasOptionsMenu(true)
    onActivityCreate(savedInstanceState)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    interstitialAd = MyAdView.getAdmobInterstitialAd(context!!)
  }

  protected abstract fun onActivityCreate(savedInstanceState: Bundle?)
}