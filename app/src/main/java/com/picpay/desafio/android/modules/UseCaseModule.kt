package com.picpay.desafio.android.modules

import com.picpay.desafio.android.usecase.FindUsersUseCase
import com.picpay.desafio.android.usecase.FindUsersUseCaseImpl
import org.koin.dsl.module

val userUseCase = module {
    factory<FindUsersUseCase> { FindUsersUseCaseImpl(userRepository = get()) }
}