package com.synergy.android.di.modules

import com.synergy.android.Router
import com.synergy.android.model.network.errors.ErrorHandler
import com.synergy.android.util.StringResource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val appModule = Kodein.Module(name = "appModule") {
    bind<StringResource>() with singleton { StringResource(instance()) }
    bind<ErrorHandler>() with singleton { ErrorHandler(instance(), instance(), instance(), instance()) }
    bind<Router>() with singleton { Router(instance()) }
}
