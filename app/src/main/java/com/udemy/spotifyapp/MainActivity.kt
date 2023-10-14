package com.udemy.spotifyapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdap: MusicAdap<Data>
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler)


        val BASE_URL = "https://deezerdevs-deezer.p.rapidapi.com"
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retroData = retrofitBuilder.getAllSongs("eminem")

                retroData.enqueue(object : Callback<MusicModel?> {
                override fun onResponse(call: Call<MusicModel?>, response: Response<MusicModel?>) {
                    val responseBody = response.body()
                    val dataList = responseBody?.data!!
                    myAdap = MusicAdap(this@MainActivity,dataList)

                    recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter= myAdap


                }
                    override fun onFailure(call: Call<MusicModel?>, t: Throwable) {
                    Log.d("failure", "${t.message}")
                }
        })

    }
}