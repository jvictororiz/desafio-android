package com.picpay.desafio.android

import com.picpay.desafio.android.data.dao.UserDaoTest
import com.picpay.desafio.android.ui.navigations.mainFlow.MainActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    UserDaoTest::class,
    MainActivityTest::class

)
class MainActivityInstrumentationTestFlow