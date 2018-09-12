package io.github.ovso.psytest;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public final class AppInitUtils {

  public static void logger() {
    Logger.addLogAdapter(new AndroidLogAdapter());
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  public static void crashlytics(Context context) {
    CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
    Fabric.with(context, new Crashlytics.Builder().core(core).build());
  }
}
