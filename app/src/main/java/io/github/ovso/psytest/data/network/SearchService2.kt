package io.github.ovso.psytest.data.network

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService2 {
  @GET("v0/b/broccoli-stg.appspot.com/o/loanExpress%2FloanExpress_test.json")
  fun getResult(@QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>): Single<JsonElement>
}