package io.github.ovso.psytest

import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
  @Test fun addition_isCorrect() {
    Assert.assertEquals(4, 2 + 2.toLong())
  }

  @Test
  fun insertAdsLoopTest() {
    val originItems = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
    val newItems = mutableListOf<String>()
    val count = originItems.count()
    for (i in 0 until count step 5) {
      val toIndex = if (i + 5 > count) count else i + 5
      println(originItems.subList(i, toIndex))
      newItems.add("ㅋ")
      newItems.addAll(originItems.subList(i, toIndex))
      println(i)
    }
    val newItems2 = ArrayList(newItems)
    println(newItems2)
  }

}