package io.github.ovso.psytest;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import timber.log.Timber;

public final class AppInitUtils {

  public static void logger() {
    Logger.addLogAdapter(new AndroidLogAdapter());
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
