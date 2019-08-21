package com.synergy.android.recovery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.synergy.android.R
import com.synergy.android.Router
import com.synergy.android.pincode.PincodeActivity.Companion.PINCODE_REQUEST_CODE
import kotlinx.android.synthetic.main.activity_pincode.bt_submit
import kotlinx.android.synthetic.main.activity_recovery.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RecoveryActivity : AppCompatActivity(), KodeinAware {

    companion object {
        const val PINCODE_VALID_EXTRA_NAME = "PINCODE_VALID_EXTRA_NAME"
    }

    override val kodein: Kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        initView()
    }

    private fun initView() {
        if (intent?.extras?.getBoolean(PINCODE_VALID_EXTRA_NAME, false) == true) {
            tv_title.text = getString(R.string.pincode_text)
            et_phone_l.visibility = View.INVISIBLE
            et_password_l.visibility = View.VISIBLE
            et_repeat_password_l.visibility = View.VISIBLE
            bt_submit.text = getString(R.string.recovery_new_button_text)
            bt_submit.setOnClickListener { navigateToProfile() }
            tv_desc.text = getString(R.string.recovery_new_desc_text)
        } else {
            tv_title.text = getString(R.string.recovery_title_text)
            et_phone.visibility = View.VISIBLE
            et_password_l.visibility = View.INVISIBLE
            et_repeat_password_l.visibility = View.INVISIBLE
            bt_submit.text = getString(R.string.recovery_button_text)
            bt_submit.setOnClickListener { navigateToPin() }
            tv_desc.text = getString(R.string.recovery_desc_text)
        }
    }

    private fun navigateToPin() {
        val router by kodein.instance<Router>()
        router.pincode(context = this)
    }


    private fun nextStep() {
        val router by kodein.instance<Router>()
        router.recovery(context = this, newStack = false)
    }

    private fun navigateToProfile() {
        val router by kodein.instance<Router>()
        router.main(context = this, clearStack = true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PINCODE_REQUEST_CODE) {
            nextStep()
        }
    }
}
