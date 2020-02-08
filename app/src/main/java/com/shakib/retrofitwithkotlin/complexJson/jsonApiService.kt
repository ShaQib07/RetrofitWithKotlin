package com.shakib.retrofitwithkotlin.complexJson

import com.shakib.retrofitwithkotlin.complexJson.models.Response
import retrofit2.Call
import retrofit2.http.GET

interface jsonApiService {

    @GET("?fbclid=IwAR0-gsweDNE8H-5mfDmlnO5RdT8coHpyJ-gRldnp4oeEw8W_4YImp3etDrg")
    fun getResponse(): Call<Response>
}