package com.sample.data.retrofit

import com.sample.data.model.Characters
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPIInterface {

    @GET("/?q=simpsons+characters&format=json")
    fun getSimpsonCharacters(): Call<Characters>

    @GET("/?q=the+wire+characters&format=json")
    fun getTheWireCharacters(): Call<Characters>
}