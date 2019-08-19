package com.synergy.android.model.network

import com.synergy.android.login.entities.LoginRequest
import com.synergy.android.login.entities.LoginResponse
import com.synergy.android.main.entities.ProfileResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface IApi {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("/v1/sessions")
    fun loginAsync(
        @Body loginRequest: LoginRequest
    ): Deferred<ApiResponse<LoginResponse>>

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("/v1/main")
    fun loadUserAsync(
        @Header("Authorization") token: String
    ): Deferred<ApiResponse<ProfileResponse>>
}
