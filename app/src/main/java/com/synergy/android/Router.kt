package com.synergy.android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.synergy.android.login.LoginActivity
import com.synergy.android.main.MainActivity
import com.synergy.android.order.OrdersActivity
import com.synergy.android.pincode.PincodeActivity
import com.synergy.android.pincode.PincodeActivity.Companion.PINCODE_REQUEST_CODE
import com.synergy.android.profile.LoyaltyActivity
import com.synergy.android.profile.ProfileEditActivity
import com.synergy.android.profile.ProfileEditNameActivity
import com.synergy.android.profile.ProfileEditNameActivity.Companion.NAME_EXTRA_NAME
import com.synergy.android.profile.ProfileEditNameActivity.Companion.NAME_REQUEST_CODE
import com.synergy.android.profile.ProfileEditNameActivity.Companion.SURNAME2_EXTRA_NAME
import com.synergy.android.profile.ProfileEditNameActivity.Companion.SURNAME_EXTRA_NAME
import com.synergy.android.profile.ProfileEditPhoneActivity
import com.synergy.android.profile.ProfileEditPhoneActivity.Companion.PHONE_EXTRA_NAME
import com.synergy.android.profile.ProfileEditPhoneActivity.Companion.PHONE_REQUEST_CODE
import com.synergy.android.recovery.RecoveryActivity
import com.synergy.android.recovery.RecoveryActivity.Companion.PINCODE_VALID_EXTRA_NAME
import com.synergy.android.registration.RegistrationActivity

class Router(private val appContext: Context) {

    fun registration(
            context: Context = appContext, clearStack: Boolean = false,
            clearTop: Boolean = false
    ) {
        context.startActivity(Intent(context, RegistrationActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            if (clearTop) {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        })
    }

    fun login(
            context: Context = appContext, clearStack: Boolean = false,
            clearTop: Boolean = false
    ) {
        context.startActivity(Intent(context, LoginActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            if (clearTop) {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        })
    }

    fun main(context: Context, clearStack: Boolean = false) {
        context.startActivity(Intent(context, MainActivity::class.java).apply {
            if (clearStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }

    fun pincode(context: AppCompatActivity, clearStack: Boolean = false) {
        context.startActivityForResult(
                Intent(context, PincodeActivity::class.java).apply {
                    if (clearStack) {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                },
                PINCODE_REQUEST_CODE
        )
    }

    fun recovery(context: Context, newStack: Boolean = true) {
        context.startActivity(Intent(context, RecoveryActivity::class.java).apply {
            if (newStack) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            } else {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtra(PINCODE_VALID_EXTRA_NAME, true)
            }
        })
    }

    fun editProfile(context: Context) {
        context.startActivity(Intent(context, ProfileEditActivity::class.java))
    }

    fun editName(context: AppCompatActivity, name: String, surname: String, surname2: String) {
        context.startActivityForResult(Intent(context, ProfileEditNameActivity::class.java)
                .apply {
                    putExtra(NAME_EXTRA_NAME, name)
                    putExtra(SURNAME_EXTRA_NAME, surname)
                    putExtra(SURNAME2_EXTRA_NAME, surname2)
                },
                NAME_REQUEST_CODE)
    }

    fun editPhone(context: AppCompatActivity, phone: String) {
        context.startActivityForResult(Intent(context, ProfileEditPhoneActivity::class.java)
                .apply {
                    putExtra(PHONE_EXTRA_NAME, phone)
                },
                PHONE_REQUEST_CODE)
    }

    fun loyalty(context: Context) {
        context.startActivity(Intent(context, LoyaltyActivity::class.java))
    }

    fun orders(context: Context) {
        context.startActivity(Intent(context, OrdersActivity::class.java))
    }
}
