package com.picpay.desafio.android.modules

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BaseApplication
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.service.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT: Long = 15L
private const val READ_TIMEOUT: Long = 15L

val applicationModule = module {
    single { buildWebService<PicPayService>((androidApplication() as BaseApplication).getApiUrl()) }
}

inline fun <reified T> buildWebService(baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(createHttpClient())
        .build()
    return retrofit.create(T::class.java)
}

fun createHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    }.build()
}