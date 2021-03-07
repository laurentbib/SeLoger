package com.biblublab.seloger

import android.app.Application
import com.biblublab.seloger.di.dispatchersModule
import com.biblublab.seloger.di.featureModule
import com.biblublab.seloger.di.networkModule
import com.biblublab.seloger.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SeLogerApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SeLogerApplication)
            modules(
                networkModule,
                dispatchersModule,
                useCaseModule,
                featureModule,
            )
        }
    }
}