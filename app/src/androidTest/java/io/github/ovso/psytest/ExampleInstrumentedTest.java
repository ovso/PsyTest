package io.github.ovso.psytest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import io.github.ovso.psytest.utils.ResourceProvider;
import io.github.ovso.psytest.utils.SchedulersFacade;
import io.github.ovso.psytest.utils.TestSchedulers;
import io.reactivex.Observable;
import io.reactivex.internal.schedulers.TrampolineScheduler;
import io.reactivex.schedulers.TestScheduler;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();

    assertEquals("io.github.ovso.psytest", appContext.getPackageName());
  }

  @Test
  public void resourceTest() {
    Context appContext = InstrumentationRegistry.getTargetContext();
    ResourceProvider resourceProvider = new ResourceProvider(appContext);
    String[] stringArray = resourceProvider.getStringArray(R.array.tabs);
    PrintStream out = System.out;

    TestSchedulers schedulers = new TestSchedulers();
    Observable.fromArray(stringArray)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(out::println);
  }
}
