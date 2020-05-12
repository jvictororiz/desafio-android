package com.picpay.desafio.android.modules

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.BaseApplication
import com.picpay.desafio.android.data.dao.databaseConfig.AplicationDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModules = module {
    single { buildDatabase(androidApplication()).userDao() }
}

fun buildDatabase(context: Context): AplicationDatabase {
    return Room.databaseBuilder(
        context,
        AplicationDatabase::class.java,
        (context as BaseApplication).getNameDatabase()
    )
        .allowMainThreadQueries().build()
}