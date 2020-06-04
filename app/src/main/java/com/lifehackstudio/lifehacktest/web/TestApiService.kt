package com.lifehackstudio.lifehacktest.web

import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TestApiService {

    @GET
    fun getCards(): Observable<List<Cards>>

    @GET
    fun getCard(@Query("id") id: Long): Maybe<Card>

    /**
     * Companion object to create the TestApiService
     */
    companion object Factory {
        fun create(): TestApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://megakohz.bget.ru/test_task/test.php")
                .build()

            return retrofit.create(TestApiService::class.java)
        }
    }

}