package com.picpay.desafio.android.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean


// To avoid writing the same extensions multiple times, we'll make an abstract class for ViewModels
abstract class BaseViewModel : ViewModel() {

    fun launch(block: suspend () -> Unit) {
        viewModelScope.launch { block() }
    }

}
