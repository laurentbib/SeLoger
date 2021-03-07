package com.biblublab.seloger

import com.biblublab.seloger.di.featureModule
import com.biblublab.seloger.di.networkModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTestRule
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import org.junit.experimental.categories.Category

@Category(CheckModuleTest::class)
class ModuleCheckTest : AutoCloseKoinTest() {

    @get:Rule
    val koinTestRule = KoinTestRule.create { modules(featureModule, networkModule) }

    @Test
    fun checkModules() = checkModules { modules(featureModule, networkModule) }
}