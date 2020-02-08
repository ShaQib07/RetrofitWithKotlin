package com.shakib.retrofitwithkotlin.complexJson

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object jsonServiceBuilder {
    private var BASE_URL = "https://api.randomuser.me/"

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}
