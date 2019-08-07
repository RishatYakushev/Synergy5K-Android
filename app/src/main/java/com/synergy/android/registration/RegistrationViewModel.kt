package com.synergy.android.registration

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.synergy.android.R
import com.synergy.android.login.LoginRepository
import com.synergy.android.model.entities.Resource
import com.synergy.android.model.entities.Session
import com.synergy.android.model.entities.Status
import com.synergy.android.util.StringResource
import com.synergy.android.util.toLiveData

class RegistrationViewModel(
        private val repository: LoginRepository,
        private val stringResource: StringResource
) : ViewModel() {

    private val signup = MutableLiveData<SignupId>()

    val signupResource: LiveData<Resource<Session>> = Transformations.switchMap(signup) {
        it.ifExists(exist = { username, surname, phone, password ->
            //            repository.signup(username, surname, phone, password)
            Resource<Session>(Status.SUCCESS, Session(0, "token"), "error")
                    .toLiveData()
        }, empty = {
            emptyResource()
        })
    }

    fun signup(username: String, surname: String, phone: String, password: String) {
        signup.postValue(SignupId(username, surname, phone, password))
    }

    @VisibleForTesting
    fun emptyResource() =
            Resource.error<Session>(stringResource.getString(R.string.empty_error)).toLiveData()

    data class SignupId(
            val username: String,
            val surname: String,
            val phone: String,
            val password: String
    ) {
        fun <T> ifExists(exist: (String, String, String, String) -> LiveData<T>,
                         empty: () -> LiveData<T>): LiveData<T> =
                if (username.isBlank() || surname.isBlank() || phone.isBlank() ||
                        password.isBlank()) {
                    empty()
                } else {
                    exist(username, surname, phone, password)
                }
    }
}
