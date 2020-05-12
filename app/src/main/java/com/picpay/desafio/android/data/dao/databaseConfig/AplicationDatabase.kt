package com.picpay.desafio.android.data.dao.databaseConfig

import androidx.room.*
import com.picpay.desafio.android.data.dao.databaseConfig.AplicationDatabase.Companion.VERSION
import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.dao.entities.UserTable

@Database(
    entities = [
        UserTable::class
    ],
    version = VERSION
)
abstract class AplicationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {

        const val NAME = "database-db"
        const val VERSION = 1

    }

}