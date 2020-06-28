package com.synergy.android.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.synergy.android.R
import kotlinx.android.synthetic.main.activity_profile_edit.toolbar
import kotlinx.android.synthetic.main.activity_profile_edit_name.*

class ProfileEditNameActivity : AppCompatActivity() {

    private var menuItem: MenuItem? = null

    private val name: String get() = intent?.extras?.getString(NAME_EXTRA_NAME) ?: ""
    private val surname: String get() = intent?.extras?.getString(SURNAME_EXTRA_NAME) ?: ""
    private val surname2: String get() = intent?.extras?.getString(SURNAME2_EXTRA_NAME) ?: ""

    private lateinit var changedName: String
    private lateinit var changedSurname: String
    private lateinit var changedSurname2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit_name)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.profile_edit_name_toolbar_title)

        initViews()

        changedName = name
        changedSurname = surname
        changedSurname2 = surname2
    }

    private fun initViews() {
        et_name.apply {
            text = SpannableStringBuilder(name)
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changedName = p0.toString()
                    checkMenuItemVisible()
                }
            })
        }
        et_surname.apply {
            text = SpannableStringBuilder(surname)
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changedSurname = p0.toString()
                    checkMenuItemVisible()
                }
            })
        }
        et_surname2.apply {
            text = SpannableStringBuilder(surname2)
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    changedSurname2 = p0.toString()
                    checkMenuItemVisible()
                }
            })
        }
    }

    private fun checkMenuItemVisible() {
        if (changedName == name && changedSurname == surname && changedSurname2 == surname2) {
            menuItem?.isEnabled = false
            menuItem?.icon?.alpha = 30
        } else {
            menuItem?.isEnabled = true
            menuItem?.icon?.alpha = 255
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_name, menu)
        menuItem = menu?.findItem(R.id.done_action)
        checkMenuItemVisible()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.done_action -> apply {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(NAME_EXTRA_NAME, changedName)
                    putExtra(SURNAME_EXTRA_NAME, changedSurname)
                    putExtra(SURNAME2_EXTRA_NAME, changedSurname2)
                })
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val NAME_EXTRA_NAME = "NAME_EXTRA_NAME"
        const val SURNAME_EXTRA_NAME = "SURNAME_EXTRA_NAME"
        const val SURNAME2_EXTRA_NAME = "SURNAME2_EXTRA_NAME"

        const val NAME_REQUEST_CODE = 3333
    }
}
