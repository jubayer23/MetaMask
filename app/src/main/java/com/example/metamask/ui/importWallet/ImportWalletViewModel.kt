package com.mindorks.retrofit.coroutines.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.metamask.repository.MainRepository
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers


class ImportWalletViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getAccount(seed: String) = liveData(Dispatchers.IO) {
        Log.d("DEBUG_ADD", seed)
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getAccount(seed)))
        } catch (exception: Exception) {
            Log.d("DEBUG", exception.message.toString())
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}