package com.synergy.android.util.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.synergy.android.R

class CheckableTextInputEditText(context: Context, attrs: AttributeSet?) :
        TextInputEditText(context, attrs) {

    private val textInputLayout: TextInputLayout by lazy {
        val til = parent.parent as TextInputLayout
        addListeners()
        til
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        textInputLayout.isErrorEnabled = true
        super.onLayout(changed, left, top, right, bottom)
    }

    private fun addListeners() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emptyValidate(p0!!)
            }
        })
        setOnEditorActionListener { p0, _, _ ->
            emptyValidate(p0.text)
            false
        }
        setOnFocusChangeListener { _, _ ->
            emptyValidate(text!!)
        }
    }

    private fun emptyValidate(char: CharSequence) {
        if (char.isEmpty()) {
            textInputLayout.error = context.getString(R.string.empty_field_text)
        } else {
            textInputLayout.error = ""
        }
    }
}