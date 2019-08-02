package com.synergy.android.model.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(@SerializedName("error") val error: ErrorBody)
