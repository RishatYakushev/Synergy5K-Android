package com.synergy.android.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.synergy.android.main.entities.Book
import com.synergy.android.main.entities.Profile
import com.synergy.android.model.db.daos.ProfileDao
import com.synergy.android.model.db.daos.SessionDao
import com.synergy.android.model.entities.Session

@Database(
    entities = [Profile::class, Book::class, Session::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun sessionDao(): SessionDao
}
