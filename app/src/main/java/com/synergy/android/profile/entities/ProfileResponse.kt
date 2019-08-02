package com.synergy.android.profile.entities

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("user")
    val profile: Profile
)
