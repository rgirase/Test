package com.sample.data.datarepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.data.model.Characters
import com.sample.data.model.RelatedTopic
import com.sample.data.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataRepository {

    var simpsoncharacter: MutableLiveData<List<RelatedTopic>>? = MutableLiveData()
    var thewirecharacters: MutableLiveData<List<RelatedTopic>>? = MutableLiveData()

    fun getSimpsonCharacters(): LiveData<List<RelatedTopic>>? {
        val call = RetrofitClient.retrofitInterface.getSimpsonCharacters()
        call.enqueue(object : Callback<Characters> {
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Characters>,
                response: Response<Characters>
            ) {
                simpsoncharacter?.value = response.body()?.RelatedTopics
            }
        })
        return simpsoncharacter
    }

    fun getTheWireCharacters(): LiveData<List<RelatedTopic>>? {
        val call = RetrofitClient.retrofitInterface.getTheWireCharacters()
        call.enqueue(object : Callback<Characters> {
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Characters>,
                response: Response<Characters>
            ) {
                thewirecharacters?.value = response.body()?.RelatedTopics
            }
        })
        return thewirecharacters
    }

}