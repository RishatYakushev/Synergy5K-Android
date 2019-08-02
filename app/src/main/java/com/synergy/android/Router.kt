package com.synergy.android

import android.content.Context
import android.content.Intent
import com.synergy.android.login.LoginActivity
import com.synergy.android.profile.ProfileActivity
import com.synergy.android.registration.RegistrationActivity

class Router(private val appContext: Context) {

    fun registration(context: Context = appContext, clearStack: Boolean = false,
                     clearTop: Boolean = false) {
        context.startActivity(Intent(context, RegistrationActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            if (clearTop) {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        })
    }

    fun login(context: Context = appContext, clearStack: Boolean = false,
              clearTop: Boolean = false) {
        context.startActivity(Intent(context, LoginActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            if (clearTop) {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        })
    }

    fun profile(context: Context, clearStack: Boolean = false) {
        context.startActivity(Intent(context, ProfileActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }
}
