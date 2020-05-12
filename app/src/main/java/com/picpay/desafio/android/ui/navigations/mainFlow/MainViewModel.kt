package com.picpay.desafio.android.ui.navigations.mainFlow

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.entities.User
import com.picpay.desafio.android.ui.base.BaseViewModel
import com.picpay.desafio.android.usecase.FindUsersUseCase

class MainViewModel(private val findUserUseCase: FindUsersUseCase) : BaseViewModel() {
    val loadUsesrObserver = MutableLiveData<Boolean>()
    val resultUsersObserver = MutableLiveData<List<User>>()
    val alertOfflineObserver = MutableLiveData<Int>()
    val errorObserver = MutableLiveData<String>()


    fun findAllUser() = launch {
        loadUsesrObserver.value = true
        val result = findUserUseCase.listAllUsers()
        if (result.isSuccessful()) {
            resultUsersObserver.value = result.data
        } else if (result.isCacheSuccessful() && result.data?.isNullOrEmpty() == false) {
            alertOfflineObserver.value = R.string.alert_offline
            resultUsersObserver.value = result.data
        } else {
            errorObserver.value = result.errorMessage()
        }
        loadUsesrObserver.value = false
    }
}