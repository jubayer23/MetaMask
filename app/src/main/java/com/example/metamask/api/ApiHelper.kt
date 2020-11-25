package com.mindorks.retrofit.coroutines.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getSeed() = apiService.getSeed()
    suspend fun getBalance( address: String) = apiService.getBalance(address)
    suspend fun getAccount( seed: String) = apiService.getAccount(seed)
}