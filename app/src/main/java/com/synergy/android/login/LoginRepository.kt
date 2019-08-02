package com.synergy.android.login

import com.synergy.android.login.entities.LoginRequest
import com.synergy.android.login.entities.LoginResponse
import com.synergy.android.model.entities.Session
import com.synergy.android.model.network.ApiResponse
import com.synergy.android.model.network.IApi
import com.synergy.android.model.network.NetworkBoundResource
import com.synergy.android.model.network.errors.ErrorHandler
import com.synergy.android.profile.AuthorizationModel
import kotlinx.coroutines.*

class LoginRepository(
        private val api: IApi,
        private val authorizationModel: AuthorizationModel,
        private val errorHandler: ErrorHandler
) : CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.IO

    fun login(
            username: String,
            password: String
    ) = object : NetworkBoundResource<Session, LoginResponse>(coroutineContext, errorHandler) {
        override suspend fun createCallAsync(): Deferred<ApiResponse<LoginResponse>> =
                api.loginAsync(LoginRequest(username, password))

        override suspend fun saveCallResult(item: LoginResponse) =
                authorizationModel.setSession(item.session)

        override suspend fun loadFromDb() = authorizationModel.getSession()
    }.asLiveData()

    fun unAuthorize() {
        launch {
            authorizationModel.unAuthorize()
        }
    }

    fun onDestroy() = coroutineContext.cancelChildren()
}
