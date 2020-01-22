package io.github.ovso.psytest.data.network

import com.google.gson.JsonElement
import io.reactivex.Single
import okhttp3.Headers
import javax.inject.Inject

class SearchRequest2 @Inject constructor() : BaseRequest<SearchService2>() {
  override val baseUrl: String
    get() = "https://firebasestorage.googleapis.com/"
  override val apiClass: Class<SearchService2>
    get() = SearchService2::class.java

  override fun createHeaders(): Headers {
    return Headers.Builder()
        .build()
  }

  fun getResult(): Single<JsonElement> {
    return api.getResult(
        mapOf(
            "alt" to "media",
            "token" to "9b163352-a23f-4ac0-865c-2381cb39ea68"
        )
    )
  }

}