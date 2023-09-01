package com.example.englern.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService private constructor() {

    val api: ChatApi
        get() = retrofit!!.create(
            ChatApi::class.java
        )

    init {

        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()


    }
companion object {
    private val BASE_URL = "http://chatenglish.avdors55.beget.tech/"

    private var apiClient: ApiService? = null
    private var retrofit: Retrofit? = null

    val instance: ApiService?
        @Synchronized get() {

            if (apiClient == null) {

                apiClient =
                    ApiService()
            }

            return apiClient


        }
}
}