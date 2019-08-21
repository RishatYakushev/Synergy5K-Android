package com.synergy.android.main

import androidx.room.withTransaction
import com.synergy.android.model.db.AppDatabase
import com.synergy.android.model.db.daos.SessionDao
import com.synergy.android.model.entities.Session

class AuthorizationModel(
        private val sessionDao: SessionDao,
        private val appDatabase: AppDatabase
) {
    suspend fun unAuthorize() = appDatabase.withTransaction {
        appDatabase.clearAllTables()
    }

    suspend fun getToken(): String = sessionDao.getToken()

    suspend fun setSession(session: Session) = sessionDao.insert(session)

    suspend fun getSession() = sessionDao.getSession()

    suspend fun isAuthorized(): Boolean = getToken().isNotBlank()
}
