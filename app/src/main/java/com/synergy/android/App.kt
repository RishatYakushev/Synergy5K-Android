package com.synergy.android

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.synergy.android.di.initKodein
import io.fabric.sdk.android.Fabric
import org.kodein.di.KodeinAware

class App : Application(), KodeinAware {
    override val kodein = initKodein(this)

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
    }
}
