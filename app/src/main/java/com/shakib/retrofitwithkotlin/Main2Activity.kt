package com.shakib.retrofitwithkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shakib.retrofitwithkotlin.complexJson.jsonApiService
import com.shakib.retrofitwithkotlin.complexJson.jsonServiceBuilder
import com.shakib.retrofitwithkotlin.complexJson.models.Response
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        loadJsonData()
    }

    private fun loadJsonData() {

        val jsonService = jsonServiceBuilder.buildService(jsonApiService::class.java)

        val requestCall = jsonService.getResponse()

        requestCall.enqueue(object: Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@Main2Activity, t.message, Toast.LENGTH_LONG).show()
                Log.d("ERROR", t.message!!)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful){
                    val jsonData = response.body()!!
                    progress.toggleVisibility()

                    val options: RequestOptions = RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round)



                    Glide.with(this@Main2Activity).load(jsonData.results!![0].picture!!.large).apply(options)
                        .into(image_user)

                    tv_name.text = "${jsonData.results!![0].name!!.title} ${jsonData.results!![0].name!!.first} ${jsonData.results!![0].name!!.last}"
                    tv_gender.text = jsonData.results!![0].gender
                    tv_dob.text = jsonData.results!![0].dob!!.date
                    tv_age.text = jsonData.results!![0].dob!!.age.toString()
                    tv_email.text = jsonData.results!![0].email
                    tv_cell.text = jsonData.results!![0].cell
                    tv_location.text = "${jsonData.results!![0].location!!.street!!.number.toString()}, ${jsonData.results!![0].location!!.street!!.name}, ${jsonData.results!![0].location!!.state}, ${jsonData.results!![0].location!!.city} ${jsonData.results!![0].location!!.postcode}, ${jsonData.results!![0].location!!.country}"



                }
            }

        })
    }
}
