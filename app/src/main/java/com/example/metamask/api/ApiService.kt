package com.mindorks.retrofit.coroutines.data.api

import com.example.metamask.model.Account
import com.example.metamask.model.Balance
import com.example.metamask.model.Seed
import com.example.metamask.model.User
import retrofit2.http.*

interface ApiService {

    @GET("getSeed")
    suspend fun getSeed(): Seed


    //@FormUrlEncoded
    //@GET("getBalance")
    //suspend fun getBalance(@Field("address") address: String): Balance


    @POST("getBalance")
    suspend fun getBalance(@Query("address") address: String): Balance

    @POST("getAccount")
    suspend fun getAccount(@Query("seed") address: String): Account
    //@GET("character/{id}")
    //    suspend fun getCharacter(@Path("id") id: Int): Response<Character>

    //@GET("character/{id}"
    //fun getAnimals(@Field("key") key: String): Single<List<Animal>>

    //@GET("character/{id}")
    //    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}