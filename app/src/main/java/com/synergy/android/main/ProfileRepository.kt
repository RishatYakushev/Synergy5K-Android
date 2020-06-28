package com.synergy.android.main

import com.synergy.android.main.entities.Profile
import com.synergy.android.main.entities.ProfileResponse
import com.synergy.android.model.db.daos.ProfileDao
import com.synergy.android.model.network.ApiResponse
import com.synergy.android.model.network.IApi
import com.synergy.android.model.network.NetworkBoundResource
import com.synergy.android.model.network.errors.ErrorHandler
import kotlinx.coroutines.*

class ProfileRepository(
    private val profileDao: ProfileDao,
    private val iApi: IApi,
    private val errorHandler: ErrorHandler,
    private val authorizationModel: AuthorizationModel
) : CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.IO

    fun loadProfile() =
        object : NetworkBoundResource<Profile, ProfileResponse>(coroutineContext, errorHandler) {
            override suspend fun saveCallResult(item: ProfileResponse) =
                profileDao.insertProfileAndBooks(item.profile)

            override suspend fun loadFromDb(): Profile? = profileDao.getProfileWithBooks()

            override suspend fun createCallAsync(): Deferred<ApiResponse<ProfileResponse>> =
                iApi.loadUserAsync(authorizationModel.getToken())

        }

    fun onDestroy() {
        coroutineContext.cancelChildren()
    }
}
