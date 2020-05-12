package com.picpay.desafio.android.usecase

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.data.dao.entities.UserTable
import com.picpay.desafio.android.data.entities.ResultRest
import com.picpay.desafio.android.data.entities.User
import com.picpay.desafio.android.service.UserRepository
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


class FindUsersUseCaseImplTest : BaseTest() {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var userUseCase: FindUsersUseCase


    @Before
    fun init() {
        userUseCase = FindUsersUseCaseImpl(userRepository)
    }

    @Test
    fun `when FindUsersUseCase calls listAllOnline with success then compare between expected and returned`() {
        val expectedUsers = listOf(User("img", "name", 0, "username"))
        var resultUsers: ResultRest<List<User>?>? = null
        runBlocking {
            whenever(userRepository.listAll()).thenReturn(ResultRest.success(expectedUsers))
            resultUsers = userUseCase.listAllUsers()
        }
        assertNotNull(resultUsers)
        resultUsers?.let {
            assertTrue(it.isSuccessful())
            assertEquals(expectedUsers, it.data)
        }
    }

    @Test
    fun `when FindUsersUseCase calls listAll with error then find offline`() {
        val expectedUsers = listOf(UserTable(0, "username", "img", "name"))
        var resultUsers: ResultRest<List<User>?>? = null
        runBlocking {
            whenever(userRepository.listAll()).thenReturn(ResultRest.error("", 404))
            whenever(userRepository.listAllCache()).thenReturn(expectedUsers)
            resultUsers = userUseCase.listAllUsers()
        }
        assertNotNull(resultUsers)
        resultUsers?.let {
            assertFalse(it.isSuccessful())
            assertTrue(it.isCacheSuccessful())
            assertNotNull(it.data)
        }
    }

    @Test
    fun `when FindUsersUseCase calls listAll offline and has no data saved return error`()  {
        var resultUsers: ResultRest<List<User>?>? = null
        runBlocking {
            whenever(userRepository.listAll()).thenReturn(ResultRest.error("", 404))
            whenever(userRepository.listAllCache()).thenReturn(null)
            resultUsers = userUseCase.listAllUsers()
        }
        //Assert
        assertNotNull(resultUsers)
        resultUsers?.let {
            assertFalse(it.isSuccessful())
            assertFalse(it.isCacheSuccessful())
            assertNull(it.data)
        }
    }


}