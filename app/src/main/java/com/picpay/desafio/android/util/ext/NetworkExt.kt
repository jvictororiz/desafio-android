package com.picpay.desafio.android.util.ext


import com.google.gson.Gson
import com.picpay.desafio.android.data.entities.ResultRest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.net.ConnectException


suspend fun <T> Call<T>.backgroundCall(dispatcher: CoroutineDispatcher): ResultRest<T?> {
    return withContext(context = dispatcher) {
        try {
            val response = this@backgroundCall.execute()
            if (response.isSuccessful) {
                ResultRest.success(response.body(), response.code())
            } else {
                val error =
                    Gson().fromJson(response.errorBody()?.string(), ErrorDefault::class.java)
                ResultRest.error(error.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is ConnectException || e is java.net.UnknownHostException) {
                ResultRest.error<T?>(ConnectException("Seu dispositivo está sem internet."))
            } else {
                ResultRest.error(e)
            }
        }
    }
}

data class ErrorDefault(val message: String)

