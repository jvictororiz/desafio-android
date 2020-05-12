package com.picpay.desafio.android.data.dao

import androidx.annotation.VisibleForTesting
import androidx.room.*
import com.picpay.desafio.android.data.dao.entities.UserTable

@Dao
interface UserDao {
    @Query("SELECT * from UserTable")
    fun findAll(): List<UserTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(listUsers: List<UserTable>)

    @VisibleForTesting
    @Query("DELETE FROM UserTable")
    fun clearAll()
}
