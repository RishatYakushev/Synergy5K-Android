package com.synergy.android.util.ui

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy
import com.synergy.android.R

class CheckableTextInputEditText(context: Context, attrs: AttributeSet?) :
    TextInputEditText(context, attrs) {

    private val textInputLayout: TextInputLayout by lazy {
        val til = parent.parent as TextInputLayout
        addListeners(til)
        til
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        textInputLayout.isErrorEnabled = true
    }

    private fun addListeners(textInputLayout: TextInputLayout) {
        when (inputType) {
            InputType.TYPE_CLASS_PHONE -> {
                val phoneListener = MaskedTextChangedListener.Companion.installOn(
                    this,
                    "+7 ([000]) [000]-[00]-[00]",
                    affinityCalculationStrategy = AffinityCalculationStrategy.PREFIX,
                    listener = object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                            emptyValidate(p0?.length != 18)
                        }

                        override fun beforeTextChanged(
                            p0: CharSequence?, p1: Int, p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    }
                )
                hint = phoneListener.placeholder()
                textInputLayout.hint = ""
            }
            else -> {
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {}

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        emptyValidate(p0?.isEmpty() ?: true)
                    }
                })
                setOnEditorActionListener { p0, _, _ ->
                    emptyValidate(p0.text.isEmpty())
                    false
                }
                setOnFocusChangeListener { _, _ ->
                    emptyValidate(text?.isEmpty() ?: true)
                }
            }
        }
    }

    private fun emptyValidate(isEmpty: Boolean) {
        if (isEmpty) {
            textInputLayout.error = errorMessage()
        } else {
            textInputLayout.error = ""
        }
    }

    private fun errorMessage(): String = when (inputType) {
        InputType.TYPE_CLASS_PHONE -> context.getString(R.string.phone_validate_text)
        else -> context.getString(R.string.empty_field_text)
    }
}