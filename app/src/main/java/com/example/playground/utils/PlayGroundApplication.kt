package com.example.playground.utils

import android.app.Application
import timber.log.Timber

class PlayGroundApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}