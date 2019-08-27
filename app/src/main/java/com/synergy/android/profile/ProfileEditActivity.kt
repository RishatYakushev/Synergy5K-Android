package com.synergy.android.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.synergy.android.R
import com.synergy.android.Router
import com.synergy.android.profile.ProfileEditNameActivity.Companion.NAME_EXTRA_NAME
import com.synergy.android.profile.ProfileEditNameActivity.Companion.NAME_REQUEST_CODE
import com.synergy.android.profile.ProfileEditNameActivity.Companion.SURNAME2_EXTRA_NAME
import com.synergy.android.profile.ProfileEditNameActivity.Companion.SURNAME_EXTRA_NAME
import kotlinx.android.synthetic.main.activity_profile_edit.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ProfileEditActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.profile_edit_toolbar_title)

        Glide.with(this)
            .load(R.drawable.avatar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .optionalCircleCrop()
            .into(iv_avatar)
        tv_name.text = "Павлов Константин"
        tv_contact_email.text = "konstantink@yandex.ru"
        tv_contact_phone.text = "+7 917 123 45 67"

        val editNameClickListener = View.OnClickListener {
            val router by kodein.instance<Router>()
            router.editName(this, "Константин", "Павлов", "Павлович")
        }
        tv_title_private_name.setOnClickListener(editNameClickListener)
        tv_name.setOnClickListener(editNameClickListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == NAME_REQUEST_CODE) {
            tv_name.text = "" +
                    "${data?.getStringExtra(NAME_EXTRA_NAME)} " +
                    "${data?.getStringExtra(SURNAME_EXTRA_NAME)} " +
                    "${data?.getStringExtra(SURNAME2_EXTRA_NAME)}"
        }
    }
}
