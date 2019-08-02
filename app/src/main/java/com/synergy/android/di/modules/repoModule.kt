package com.synergy.android.di.modules

import com.synergy.android.login.LoginRepository
import com.synergy.android.profile.ProfileRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val repoModule = Kodein.Module(name = "repoModule") {
    bind<LoginRepository>() with provider { LoginRepository(instance(), instance(), instance()) }
    bind<ProfileRepository>() with provider { ProfileRepository(instance(), instance(), instance(), instance()) }
}
