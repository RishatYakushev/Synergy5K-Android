package com.synergy.android.model.network.errors

data class ServerError(
    val code: Int,
    val errorBody: String?
) : RuntimeException()
