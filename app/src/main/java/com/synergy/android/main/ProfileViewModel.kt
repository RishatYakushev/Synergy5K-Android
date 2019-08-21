package com.synergy.android.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.synergy.android.login.LoginRepository

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    @VisibleForTesting
    val profileBoundResource = profileRepository.loadProfile()
    val profileResponse = profileBoundResource.asLiveData()

    override fun onCleared() {
        profileRepository.onDestroy()
        loginRepository.onDestroy()
    }

    fun updateProfile() {
        profileBoundResource.fetchFromNetwork()
    }

    fun logout() {
        loginRepository.unAuthorize()
    }
}
