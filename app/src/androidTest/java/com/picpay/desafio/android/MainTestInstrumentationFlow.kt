package com.picpay.desafio.android

import com.picpay.desafio.android.ui.activity.MainActivityInstrumentationTest
import com.picpay.desafio.android.data.dao.UserDaoTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityInstrumentationTest::class,
    UserDaoTest::class

)
class MainActivityInstrumentationTestFlow