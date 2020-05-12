package com.picpay.desafio.android.usecase

import com.picpay.desafio.android.data.entities.ResultRest
import com.picpay.desafio.android.data.entities.User
import com.picpay.desafio.android.service.UserRepository
import com.picpay.desafio.android.util.ext.toUser
import com.picpay.desafio.android.util.ext.toUserTable

class FindUsersUseCaseImpl(val userRepository: UserRepository) : FindUsersUseCase {

    override suspend fun listAllUsers(): ResultRest<List<User>?> {
        val result = userRepository.listAll()
        if (result.isSuccessful()) {
            result.data?.let {
                userRepository.saveUsers(it.map { user -> user.toUserTable() })
            }
        } else {
            val cacheResult = userRepository.listAllCache()?.map { it.toUser() }
            if (!cacheResult.isNullOrEmpty()) {
                result.data = cacheResult
            }
        }
        return result
    }

}

interface FindUsersUseCase {
    suspend fun listAllUsers(): ResultRest<List<User>?>
}