package com.synergy.android.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.synergy.android.R
import com.synergy.android.Router
import com.synergy.android.util.observeBy
import com.synergy.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: LoginViewModel by provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.loginResource.observeBy(
                this,
                onSuccess = ::navigateToProfile,
                onError = ::showError,
                onLoading = ::setProgress
        )

        initListeners()
    }

    private fun navigateToProfile() {
        val router by kodein.instance<Router>()
        router.profile(context = this, clearStack = true)
    }

    private fun navigateToSignup() {
        val router by kodein.instance<Router>()
        router.registration(context = this, clearTop = true)
    }

    private fun initListeners() {
        bt_login.setOnClickListener { login() }
        bt_signup.setOnClickListener { navigateToSignup() }
        et_password.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    login()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun login() {
        val phone = et_phone.text.toString()
        val password = et_password.text.toString()
        viewModel.login(phone, password)
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            pb_progress.visibility = View.VISIBLE
            bt_login.isEnabled = false
        } else {
            pb_progress.visibility = View.GONE
            bt_login.isEnabled = true
        }
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }
}
