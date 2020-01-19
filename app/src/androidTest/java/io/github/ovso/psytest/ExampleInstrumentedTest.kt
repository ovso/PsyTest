package io.github.ovso.psytest

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.github.ovso.psytest.R.array
import io.github.ovso.psytest.ui.main.rvadapter.MainItem
import io.github.ovso.psytest.utils.ResourceProvider
import io.github.ovso.psytest.utils.TestSchedulers
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  @Test fun useAppContext() { // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()
    Assert.assertEquals("io.github.ovso.psytest", appContext.packageName)
  }

  @Test fun resourceTest() {
    val appContext = InstrumentationRegistry.getTargetContext()
    val resourceProvider = ResourceProvider(appContext)
    val stringArray = resourceProvider.getStringArray(array.tabs)
    val out = System.out
    val schedulers = TestSchedulers()
    Observable.fromArray(*stringArray)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe { x: String? -> out.println(x) }
  }

  @Test fun rx_zip_test() {
    val appContext = InstrumentationRegistry.getTargetContext()
    val resourceProvider = ResourceProvider(appContext)
    val titles = Observable.fromIterable(resourceProvider.getStringArray(array.tabs).toList())
    val queries = Observable.fromIterable(resourceProvider.getStringArray(array.queries).toList())

    fun onSuccess(items: List<MainItem>) {
      println(items)
    }

    fun onFailure(t: Throwable) {
      println(t)
    }

    titles.zipWith(
        queries, BiFunction<String, String, MainItem> { name, query -> MainItem(name, query) })
        .toList()
        .subscribe(::onSuccess, ::onFailure)
  }

  @Test fun kotlin_zip_test() {
    val appContext = InstrumentationRegistry.getTargetContext()
    val resourceProvider = ResourceProvider(appContext)
    val toList = resourceProvider.getStringArray(array.tabs)
        .zip(resourceProvider.getStringArray(array.queries))
        .map {
          MainItem(it.first, it.second)
        }
        .toList()
    println(toList)
  }
}