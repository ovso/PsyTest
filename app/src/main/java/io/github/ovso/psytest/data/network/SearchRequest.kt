package io.github.ovso.psytest.data.network

import android.text.TextUtils
import io.github.ovso.psytest.Security
import io.github.ovso.psytest.data.KeyName
import io.github.ovso.psytest.data.network.model.Search
import io.reactivex.Single
import java.util.HashMap
import javax.inject.Inject
import okhttp3.Headers

class SearchRequest @Inject constructor() : BaseRequest<SearchService>() {
  override val baseUrl: String
    get() = ApiEndPoint.SEARCH.url
  override val apiClass: Class<SearchService>
    get() = SearchService::class.java

  override fun createHeaders(): Headers {
    return Headers.Builder()
        .build()
  }

  fun getResult(
    q: String?,
    pageToken: String?
  ): Single<Search> {
    return api.getResult(createQueryMap(q!!, pageToken))
  }

  fun getResult2(
    params: Map<String, Any>
  ): Single<Search> {
    return api.getResult(params)
  }

  private fun createQueryMap(
    q: String,
    pageToken: String?
  ): Map<String, Any> {
    val queryMap = HashMap<String, Any>()
    queryMap[KeyName.Q.get()] = q
    queryMap[KeyName.MAX_RESULTS.get()] = 50
    queryMap[KeyName.ORDER.get()] = "viewCount"
    queryMap[KeyName.TYPE.get()] = "video"
    queryMap[KeyName.VIDEO_SYNDICATED.get()] = "any"
    queryMap[KeyName.KEY.get()] = Security.KEY.value
    queryMap[KeyName.PART.get()] = "snippet"
    if (!TextUtils.isEmpty(pageToken)) {
      queryMap[KeyName.PAGE_TOKEN.get()] = pageToken!!
    }
    return queryMap
  }
}