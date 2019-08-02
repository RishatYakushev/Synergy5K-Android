package com.synergy.android.di

import android.app.Application
import com.synergy.android.di.modules.*
import org.kodein.di.Kodein
import org.kodein.di.android.x.androidXModule

fun initKodein(app: Application) =
    Kodein.lazy {
        import(androidXModule(app))
        import(dbModule)
        import(appModule)
        import(viewModelModule)
        import(netModule)
        import(repoModule)
    }
