package com.mindorks.retrofit.coroutines.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.metamask.repository.MainRepository
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.HomeViewModel
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.ImportWalletViewModel
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.CreateWalletViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateWalletViewModel::class.java)) {
            return CreateWalletViewModel(MainRepository(apiHelper)) as T
        }
        else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MainRepository(apiHelper)) as T
        }
       else if (modelClass.isAssignableFrom(ImportWalletViewModel::class.java)) {
            return ImportWalletViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

