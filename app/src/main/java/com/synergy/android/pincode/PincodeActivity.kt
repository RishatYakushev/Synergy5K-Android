package com.synergy.android.pincode

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.poovam.pinedittextfield.PinField
import com.synergy.android.R
import kotlinx.android.synthetic.main.activity_pincode.*

class PincodeActivity : AppCompatActivity() {

    private lateinit var pincode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pincode)

        ov_pincode.onTextCompleteListener = (object : PinField.OnTextCompleteListener {
            override fun onTextComplete(enteredText: String): Boolean {
                Toast.makeText(this@PincodeActivity, enteredText, Toast.LENGTH_LONG).show()
                return true
            }
        })
    }
}
