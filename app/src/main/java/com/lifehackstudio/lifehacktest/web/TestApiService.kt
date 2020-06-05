package com.lifehackstudio.lifehacktest.web

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApiService {

    @GET("test.php")
    fun getCards(): Observable<List<Cards>>

    @GET("test.php")
    fun getCard(@Query("id") id: Long): Observable<List<Card>>

    /**
     * Companion object to create the TestApiService
     */
    companion object Factory {
        private var testApiService: TestApiService? = null
        fun invoke(): TestApiService {
            if (testApiService == null) {
                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://megakohz.bget.ru/test_task/")
                    .build()

                testApiService = retrofit.create(TestApiService::class.java)
            }

            return testApiService!!
        }
    }

}