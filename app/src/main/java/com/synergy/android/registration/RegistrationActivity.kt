package com.synergy.android.registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.synergy.android.R
import com.synergy.android.Router
import com.synergy.android.pincode.PincodeActivity
import com.synergy.android.util.observeBy
import com.synergy.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RegistrationActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: RegistrationViewModel by provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        viewModel.signupResource.observeBy(
                this,
                onSuccess = ::navigateToPincode,
                onError = ::showError,
                onLoading = ::setProgress
        )

        initListeners()
    }

    private fun navigateToPincode() {
        val router by kodein.instance<Router>()
        router.pincode(context = this)
    }

    private fun navigateToLogin() {
        val router by kodein.instance<Router>()
        router.login(context = this, clearTop = true)
    }

    private fun navigateToProfile() {
        val router by kodein.instance<Router>()
        router.main(context = this, clearStack = true)
    }

    private fun initListeners() {
        bt_signup.setOnClickListener {
            signup()
        }
        bt_signin.setOnClickListener { navigateToLogin() }
        et_password.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    signup()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun signup() {
        val username = et_name.text.toString()
        val surname = et_surname.text.toString()
        val phone = et_phone.text.toString()
        val password = et_password.text.toString()
        viewModel.signup(username, surname, phone, password)
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            pb_progress.visibility = View.VISIBLE
            bt_signup.isEnabled = false
        } else {
            pb_progress.visibility = View.GONE
            bt_signup.isEnabled = true
        }
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PincodeActivity.PINCODE_REQUEST_CODE) {
            navigateToProfile()
        }
    }
}
