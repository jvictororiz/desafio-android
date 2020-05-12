package com.picpay.desafio.android.service

import com.picpay.desafio.android.base.BaseServiceTest
import com.picpay.desafio.android.data.service.PicPayService
import org.junit.Assert.*
import org.junit.Test


internal class UserServiceTest : BaseServiceTest() {

    @Test
    fun `Must list all users  successfully`() {
        val mockResponse = mockSuccessfulResponse("/json/response_users.json")
        mockWebServer.enqueue(mockResponse)
        val response = buildWebServiceTest<PicPayService>().getUsers().execute()
        assertNotNull(response.body())
        assertTrue(response.isSuccessful)
    }

    @Test
    fun `Must list all users  in error`() {
        val mockResponse = mockUnsuccessfulResponse()
        mockWebServer.enqueue(mockResponse)
        val response = buildWebServiceTest<PicPayService>().getUsers().execute()
        assertNull(response.body())
        assertFalse(response.isSuccessful)
    }
}
