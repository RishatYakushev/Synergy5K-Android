package com.synergy.android.util.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import com.synergy.android.R

class GradientMaterialButton(context: Context, attrs: AttributeSet?) :
        MaterialButton(context, attrs) {
    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        paint.shader = LinearGradient(
                0f, 0f, width.toFloat(), 0f,
                context.resources.getColor(R.color.gradient_text_0),
                context.resources.getColor(R.color.gradient_text_1),
                Shader.TileMode.CLAMP
        )
        super.onLayout(changed, left, top, right, bottom)
    }
}