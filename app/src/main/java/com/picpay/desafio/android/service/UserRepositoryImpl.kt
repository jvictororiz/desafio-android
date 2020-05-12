package com.picpay.desafio.android.service

import com.picpay.desafio.android.util.ext.backgroundCall
import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.dao.entities.UserTable
import com.picpay.desafio.android.data.entities.ResultRest
import com.picpay.desafio.android.data.entities.User
import com.picpay.desafio.android.data.service.PicPayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface UserRepository {
    suspend fun listAll(): ResultRest<List<User>?>
    suspend fun listAllCache(): List<UserTable>?
    suspend fun saveUsers(users: List<UserTable>)
}


class UserRepositoryImpl(
    private val picPayService: PicPayService,
    private val userDao: UserDao
): UserRepository {

    override suspend fun listAll() = picPayService.getUsers().backgroundCall(Dispatchers.IO)

    override suspend fun listAllCache() = withContext(Dispatchers.Default) { userDao.findAll() }

    override suspend fun saveUsers(users: List<UserTable>) = withContext(Dispatchers.Default) { userDao.saveAll(users) }

}

