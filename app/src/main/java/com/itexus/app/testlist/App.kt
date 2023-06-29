package com.itexus.app.testlist

import android.app.Application
import com.itexus.app.data.di.dataModule
import com.itexus.app.logic.di.logicModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(logicModule + dataModule)
        }
    }
}