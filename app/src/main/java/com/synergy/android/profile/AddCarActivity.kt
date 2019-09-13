package com.synergy.android.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy
import com.synergy.android.R
import kotlinx.android.synthetic.main.activity_add_car.*

class AddCarActivity : AppCompatActivity() {

    private var menuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.profile_add_car2)

        initViews()
        initAdapter()
    }

    private fun initViews() {
        Glide.with(this)
            .load(R.drawable.ic_car_placeholder)
            .fitCenter()
            .into(iv_car)

        val numberListener = MaskedTextChangedListener.installOn(
            et_number,
            "[A] [000] [AA]  [000] RUS",
            affinityCalculationStrategy = AffinityCalculationStrategy.PREFIX,
            listener = object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            }
        )
        et_number.hint = numberListener.placeholder()
        et_number_l.hint = ""

        val vinListener = MaskedTextChangedListener.installOn(
            et_vin,
            "[AAAA] [0000] [000000]",
            affinityCalculationStrategy = AffinityCalculationStrategy.PREFIX,
            listener = object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            }
        )
        et_vin.hint = vinListener.placeholder()
        et_vin_l.hint = ""
    }

    private fun initAdapter() {
        val arrayAdapter = ArrayAdapter<String>(
            this, R.layout.item_spinner_dropdown,
            listOf("select", "sedan", "crossover", "coupe")
        )
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner)
        sn_type.adapter = arrayAdapter
        sn_brand.adapter = arrayAdapter
        sn_model.adapter = arrayAdapter
        sn_year.adapter = arrayAdapter
    }

    private fun checkMenuItemVisible(visible: Boolean = false) {
        if (visible) {
            menuItem?.isEnabled = true
            menuItem?.icon?.alpha = 255
        } else {
            menuItem?.isEnabled = false
            menuItem?.icon?.alpha = 30
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
        }
        return super.onOptionsItemSelected(item)
    }
}
