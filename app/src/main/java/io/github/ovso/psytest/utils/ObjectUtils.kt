package io.github.ovso.psytest.utils

object ObjectUtils {

  fun isEmpty(`object`: Any?): Boolean {
    return `object` == null
  }

  fun isEmpty(array: Array<Any>?): Boolean {
    return array == null || array.isEmpty()
  }

  fun isEmpty(str: CharSequence?): Boolean {
    return str == null || str.isEmpty()
  }

  fun isEmpty(collection: Collection<*>?): Boolean {
    return collection == null || collection.isEmpty()
  }

  fun isEmpty(map: Map<*, *>?): Boolean {
    return map == null || map.isEmpty()
  }

  fun isEmpty(list: List<*>?): Boolean {
    return list == null || list.isEmpty()
  }
}
