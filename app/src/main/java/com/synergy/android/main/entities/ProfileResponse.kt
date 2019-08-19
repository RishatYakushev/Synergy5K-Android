package com.synergy.android.main.entities

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("user")
    val profile: Profile
)
