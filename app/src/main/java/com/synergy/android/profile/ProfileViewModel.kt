package com.synergy.android.profile

import androidx.lifecycle.ViewModel
import com.synergy.android.login.LoginRepository

class ProfileViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    fun logout() {
        loginRepository.unAuthorize()
    }
}
