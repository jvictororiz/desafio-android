package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.dao.databaseConfig.AplicationDatabase
import com.picpay.desafio.android.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class BaseApplication : Application() {
    open fun getApiUrl(): String {
        return BuildConfig.SERVER_URL
    }

    open fun getNameDatabase(): String{
        return AplicationDatabase.NAME
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            modules(
                listOf(
                    applicationModule,
                    repositoryModule,
                    databaseModules,
                    viewModelModules,
                    userUseCase
                )
            )
        }
    }
}