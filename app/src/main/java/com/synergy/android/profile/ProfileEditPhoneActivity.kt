package com.synergy.android.profile

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.poovam.pinedittextfield.PinField
import com.synergy.android.R
import com.synergy.android.util.Keyboard
import kotlinx.android.synthetic.main.activity_profile_edit.toolbar
import kotlinx.android.synthetic.main.activity_profile_edit_phone.*

class ProfileEditPhoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit_phone)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.profile_edit_phone_toolbar_title)

        switchToPincode(false)
        bt_submit.setOnClickListener {
            switchToPincode(true)
            Keyboard.hide(et_phone)
        }

        ov_pincode.onTextCompleteListener = (object : PinField.OnTextCompleteListener {
            override fun onTextComplete(enteredText: String): Boolean {
                showDialog()
                return true
            }
        })
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Константин К")
            .setMessage("Номер телефона изменен")
            .setCancelable(false)
            .setPositiveButton("Все понятно", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            })
            .create()
            .show()
    }

    private fun switchToPincode(pincodeView: Boolean) {
        if (!pincodeView) {
            tv_title.text = getString(R.string.profile_edit_phone_put_phone)
            et_phone_l.visibility = View.VISIBLE
            ov_pincode.visibility = View.GONE
            bt_submit.text = getString(R.string.profile_edit_phone_button)
        } else {
            tv_title.text = getString(R.string.profile_edit_phone_put_pincode)
            et_phone_l.visibility = View.GONE
            ov_pincode.visibility = View.VISIBLE
            bt_submit.text = getString(R.string.confirm_text)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val PHONE_REQUEST_CODE = 4444
        const val PHONE_EXTRA_NAME = "PHONE_EXTRA_NAME"
    }
}
