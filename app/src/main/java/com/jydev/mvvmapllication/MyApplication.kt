package com.jydev.riiidsimapleapllication

import android.app.Application
import com.jydev.riiidsimapleapllication.di.dataSourceModule
import com.jydev.riiidsimapleapllication.di.networkModule
import com.jydev.riiidsimapleapllication.di.repositoryModule
import com.jydev.riiidsimapleapllication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(networkModule)
            modules(viewModelModule)
        }
    }
}