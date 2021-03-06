package com.synergy.android.di.modules

import android.content.Context
import androidx.room.Room
import com.synergy.android.main.AuthorizationModel
import com.synergy.android.model.db.AppDatabase
import com.synergy.android.model.db.daos.ProfileDao
import com.synergy.android.model.db.daos.SessionDao
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dbModule = Kodein.Module(name = "dbModule") {
    bind<AppDatabase>() with singleton { provideDatabase(instance()) }

    bind<ProfileDao>() with singleton { instance<AppDatabase>().profileDao() }
    bind<SessionDao>() with singleton { instance<AppDatabase>().sessionDao() }

    bind<AuthorizationModel>() with singleton { AuthorizationModel(instance(), instance()) }
}

private fun provideDatabase(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase")
        .fallbackToDestructiveMigration()
        .build()
