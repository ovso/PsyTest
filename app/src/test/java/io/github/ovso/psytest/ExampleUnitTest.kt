package io.github.ovso.psytest

import com.google.gson.JsonElement
import io.github.ovso.psytest.data.KeyName
import io.github.ovso.psytest.data.network.SearchRequest
import io.github.ovso.psytest.data.network.SearchRequest2
import io.github.ovso.psytest.data.network.model.Search
import io.github.ovso.psytest.data.network.model.SearchItem
import io.github.ovso.psytest.utils.TestSchedulers
import org.junit.Test
import java.util.Collections

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

  private val searchRequest: SearchRequest = SearchRequest()
  private var nextPageToken: String? = null
  private val schedulers: TestSchedulers = TestSchedulers()

  @Test
  fun req_search_test() {
    fun onSuccess(search: Search) {
//      println(search.items?.size)
      search.items?.forEach {
        println(it.id?.videoId.isNullOrEmpty())
      }
    }

    fun onFailure(t: Throwable) {
      println("onFailure")
      println(t)
    }

    val params = mapOf(
        KeyName.KEY.get() to Security.KEY.value,
        KeyName.Q.get() to "심리테스트",
        KeyName.MAX_RESULTS.get() to 6,
        KeyName.ORDER.get() to "viewCount",
        KeyName.TYPE.get() to "video",
        KeyName.VIDEO_SYNDICATED.get() to "any",
        KeyName.PART.get() to "snippet"
    ).apply {
      if (nextPageToken.isNullOrEmpty().not()) {
        KeyName.PAGE_TOKEN.get() to nextPageToken
      }
    }

    fun shuffle(search: Search): Search {
      Collections.shuffle(search.items)
      return search
    }

    fun insertAds(search: Search): Search {
      val oldItems = search.items
      val newItems = mutableListOf<SearchItem>()
      val count = oldItems!!.count()
      val stepCount = 5
      for (i in 0 until count step stepCount) {
        val toIndex = if (i + stepCount > count) count else i + stepCount
        newItems.add(SearchItem()) // null properties
        newItems.addAll(oldItems.subList(i, toIndex))
      }
      search.items = newItems
      return search
    }

    searchRequest.getResult2(params)
        .map(::shuffle)
        .map(::insertAds)
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(::onSuccess, ::onFailure)
  }

  @Test
  fun insertAdsLoopTest() {
    val originItems = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    val newItems = mutableListOf<String>()
    val count = originItems.count()
    val stepCount = 5
    for (i in 0 until count step stepCount) {
      val toIndex = if (i + stepCount > count) count else i + stepCount
      println(originItems.subList(i, toIndex))
      newItems.add("ㅋ")
      newItems.addAll(originItems.subList(i, toIndex))
      println(i)
    }
    val newItems2 = ArrayList(newItems)
    println(newItems2)
  }

  @Test
  fun jsonTest() {
    //https://firebasestorage.googleapis.com/v0/b/broccoli-stg.appspot.com/o/loanExpress%2FloanExpress_test.json?alt=media&token=9b163352-a23f-4ac0-865c-2381cb39ea68
    fun onSuccess(j: JsonElement) {
      println(j.toString())
    }

    fun onError(t: Throwable) {
      println(t)
    }

    SearchRequest2().getResult()
        .subscribeOn(schedulers.io())
        .observeOn(schedulers.ui())
        .subscribe(::onSuccess, ::onError)
  }
}