package com.synergy.android.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.synergy.android.BuildConfig
import com.synergy.android.login.entities.LoginRequest
import com.synergy.android.login.entities.LoginResponse
import com.synergy.android.model.entities.Session
import com.synergy.android.model.network.ApiErrorResponse
import com.synergy.android.model.network.ApiResponse
import com.synergy.android.model.network.ApiSuccessResponse
import com.synergy.android.model.network.IApi
import com.synergy.android.model.network.adapter.CoroutineCallAdapterFactory
import com.synergy.android.model.network.errors.ServerError
import com.synergy.android.profile.entities.Book
import com.synergy.android.profile.entities.Profile
import com.synergy.android.profile.entities.ProfileResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.with
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = Kodein.Module(name = "netModule") {
    constant("baseUrl") with BuildConfig.BASE_URL

    bind<Gson>() with singleton {
        GsonBuilder().create()
    }
    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
            .build()
    }
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl(instance<String>("baseUrl"))
            .addConverterFactory(GsonConverterFactory.create(instance()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(instance())
            .build()
    }
    @Suppress("MagicNumber")
    bind<IApi>() with singleton {
        //        instance<Retrofit>().create(IApi::class.java)
        object : IApi {
            override fun loadUserAsync(token: String): Deferred<ApiResponse<ProfileResponse>> =
                when (token) {
                    "No Favorite Book Token" -> CompletableDeferred<ApiResponse<ProfileResponse>>(
                        ApiSuccessResponse(ProfileResponse(noFavBookProfile))
                    )
                    else -> CompletableDeferred<ApiResponse<ProfileResponse>>(
                        ApiSuccessResponse(ProfileResponse(profile))
                    )
                }

            override fun loginAsync(loginRequest: LoginRequest): Deferred<ApiResponse<LoginResponse>> =
                when (loginRequest.username) {
                    "Timur" -> CompletableDeferred<ApiResponse<LoginResponse>>(
                        ApiErrorResponse(ServerError(401, errorJson))
                    )
                    else -> CompletableDeferred<ApiResponse<LoginResponse>>(
                        ApiSuccessResponse(LoginResponse(session))
                    )
                }
        }
    }
}

@Suppress("MagicNumber") val profile = Profile(0, "John Snow", null).also { profile ->
    profile.favoriteBook = Book(4, "#$%^&*( ./.", 16)
    profile.booksRead = ArrayList<Book>().also {
        it.add(Book(0, "Hey hey", 1))
        it.add(Book(2, "GoT 5", 15))
    }
}

@Suppress("MagicNumber") val noFavBookProfile = Profile(0, "John Snow", null).also { profile ->
    profile.booksRead = ArrayList<Book>().also {
        it.add(Book(0, "Hey hey", 1))
        it.add(Book(2, "GoT 5", 15))
    }
}

private val session = Session(0, "token")

private val errorJson =
    "{\n" + "           \"error\": {\n" + "               \"status\": 401,\n" +
    "               \"error\": \"Authentication failed\"\n" + "           }\n" + "       }"
