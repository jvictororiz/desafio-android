package com.picpay.desafio.android.modules

import com.picpay.desafio.android.service.UserRepository
import com.picpay.desafio.android.service.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(userDao = get(), picPayService = get()) }
}