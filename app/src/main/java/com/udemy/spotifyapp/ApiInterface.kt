package com.udemy.spotifyapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: fb30048fcdmshd16cdba57f07cfcp16f7bajsn3a9f3d6e78ac",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("/search")
    fun getAllSongs (@Query("q") query:String) : Call<MusicModel>

}