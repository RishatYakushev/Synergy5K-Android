package com.synergy.android.model.network.errors

import androidx.annotation.VisibleForTesting
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.synergy.android.Router
import com.synergy.android.main.AuthorizationModel
import com.synergy.android.model.network.ErrorResponse
import com.synergy.android.util.StringResource
import com.synergy.android.util.userMessage
import retrofit2.HttpException
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class ErrorHandler(
    private val gson: Gson,
    private val stringResource: StringResource,
    private val authorizationModel: AuthorizationModel,
    private val router: Router
) {
    suspend fun proceed(error: Throwable): String = when (error) {
        is HttpException -> {
            if (error.code() == HTTP_UNAUTHORIZED) unAuthorize()
            error.userMessage(stringResource)
        }
        is ServerError -> {
            if (error.code == HTTP_UNAUTHORIZED) unAuthorize()
            try {
                val errorBody = gson.fromJson(error.errorBody, ErrorResponse::class.java)?.error
                errorBody?.error
            } catch (e: JsonSyntaxException) {
                error.userMessage(stringResource)
            } ?: error.userMessage(stringResource)
        }
        else -> error.userMessage(stringResource)
    }

    @VisibleForTesting
    suspend fun unAuthorize() {
        authorizationModel.unAuthorize()
        router.login(clearStack = true)
    }
}
