package com.mo22sen.smsbackup.application

import android.app.Application
import com.mo22sen.smsbackup.BuildConfig
import com.mo22sen.smsbackup.di.appModule
import com.mo22sen.smsbackup.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Plant Timber logger in
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    appModule, viewModelModule
                )
            )
        }
    }
}