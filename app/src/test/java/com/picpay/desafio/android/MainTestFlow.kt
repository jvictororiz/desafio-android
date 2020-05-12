package com.picpay.desafio.android

import com.picpay.desafio.android.service.UserServiceTest
import com.picpay.desafio.android.ui.viewModel.MainViewModelTest
import com.picpay.desafio.android.usecase.FindUsersUseCaseImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainViewModelTest::class,
    FindUsersUseCaseImplTest::class,
    UserServiceTest::class
)
class MainActivityTest