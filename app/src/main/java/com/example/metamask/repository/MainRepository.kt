package com.example.metamask.repository

import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import java.net.Inet4Address

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getSeed()
    suspend fun getBalance(address: String) = apiHelper.getBalance(address)
    suspend fun getAccount(seed: String) = apiHelper.getAccount(seed)
}