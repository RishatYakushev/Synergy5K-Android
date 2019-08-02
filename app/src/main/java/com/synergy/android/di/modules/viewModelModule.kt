package com.synergy.android.di.modules

import androidx.lifecycle.ViewModelProvider
import com.synergy.android.login.LoginViewModel
import com.synergy.android.profile.ProfileViewModel
import com.synergy.android.util.ViewModelFactory
import com.synergy.android.util.bindViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val viewModelModule = Kodein.Module(name = "viewModelModule") {
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(dkodein) }

    bindViewModel<LoginViewModel>() with provider { LoginViewModel(instance(), instance()) }
    bindViewModel<ProfileViewModel>() with provider { ProfileViewModel(instance(), instance()) }
}
