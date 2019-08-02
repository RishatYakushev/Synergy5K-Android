package com.synergy.android.login.entities

import com.google.gson.annotations.SerializedName
import com.synergy.android.model.entities.Session

data class LoginResponse(@SerializedName("session") val session: Session)
