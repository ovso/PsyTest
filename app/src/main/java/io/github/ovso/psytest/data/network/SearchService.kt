package io.github.ovso.psytest.data.network

import io.github.ovso.psytest.data.network.model.Search
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService {
  @GET("youtube/v3/search")
  fun getResult(@QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>): Single<Search>
}