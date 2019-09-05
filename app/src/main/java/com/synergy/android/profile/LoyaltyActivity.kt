package com.synergy.android.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.MenuItem
import android.view.MotionEvent.ACTION_UP
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.synergy.android.R
import kotlinx.android.synthetic.main.activity_loyalty.*

class LoyaltyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loyalty)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.loyalty_toolbar_title)

        et_code.text = SpannableStringBuilder("123 123 123 123")
        et_code.setOnTouchListener { _, motionEvent ->
            val drawableRight = 2
            if (motionEvent.action == ACTION_UP) {
                val diff = et_code.right -
                        et_code.compoundDrawables[drawableRight].bounds.width()
                if (motionEvent.rawX >= diff) {
                    val clipboard =
                        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("referral code", et_code.text)
                    clipboard.setPrimaryClip(clip)
                    true
                }
            }
            false
        }

        Glide.with(this)
            .load(R.drawable.qr_code)
            .transition(withCrossFade())
            .into(iv_qrcode)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
