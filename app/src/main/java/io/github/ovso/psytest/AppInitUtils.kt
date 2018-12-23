package io.github.ovso.psytest

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.android.gms.ads.MobileAds
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.fabric.sdk.android.Fabric
import timber.log.Timber

object AppInitUtils {

  fun logger() {
    Logger.addLogAdapter(AndroidLogAdapter())
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  fun crashlytics(context: Context) {
    val core = CrashlyticsCore.Builder()
        .disabled(!BuildConfig.DEBUG)
        .build()
    Fabric.with(context, Crashlytics.Builder().core(core).build())
  }

  fun ads(context: Context) {
    MobileAds.initialize(context, Security.ADMOB_APP_ID.value)
  }
}