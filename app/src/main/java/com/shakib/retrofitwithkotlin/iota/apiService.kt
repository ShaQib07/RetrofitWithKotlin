package com.shakib.retrofitwithkotlin.iota

import retrofit2.Call
import retrofit2.http.GET

interface apiService {

    @GET("event_task")
    fun getGroups(): Call<IOTA>
}