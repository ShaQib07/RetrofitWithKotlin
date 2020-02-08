package com.shakib.retrofitwithkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.shakib.retrofitwithkotlin.complexJson.jsonApiService
import com.shakib.retrofitwithkotlin.complexJson.jsonServiceBuilder
import com.shakib.retrofitwithkotlin.iota.IOTA
import com.shakib.retrofitwithkotlin.iota.apiService
import com.shakib.retrofitwithkotlin.iota.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            progress_bar.toggleVisibility()
            loadData()
        }

        btn_user.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
    }

    private fun loadData() {

        val iotaService = ServiceBuilder.buildService(apiService::class.java)

        val requestCall = iotaService.getGroups()

        requestCall.enqueue(object: Callback<IOTA> {
            override fun onFailure(call: Call<IOTA>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                Log.d("ERROR", t.message!!)
            }

            override fun onResponse(call: Call<IOTA>, response: Response<IOTA>) {
                if (response.isSuccessful){
                    val iotaData = response.body()!!
                    progress_bar.toggleVisibility()

                    tv_a.text = iotaData.groupA
                    tv_b.text = iotaData.groupB
                    tv_c.text = iotaData.groupC
                    tv_d.text = iotaData.groupD
                    tv_e.text = iotaData.groupE
                    tv_f.text = iotaData.groupF
                    tv_g.text = iotaData.groupG
                }
            }

        })
    }

}
