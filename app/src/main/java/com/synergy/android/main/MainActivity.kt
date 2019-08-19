package com.synergy.android.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.synergy.android.R
import com.synergy.android.util.observeBy
import com.synergy.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class MainActivity : AppCompatActivity(), KodeinAware, SwipeRefreshLayout.OnRefreshListener {

    override val kodein: Kodein by kodein()

    private val viewModel: ProfileViewModel by provideViewModel()

    private var wheelFilter = false
    private var washFilter = false
    private var serviceFilter = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.profileResponse.observeBy(
                this,
                onNext = {
                    showProfile()
                },
                onError = ::showError,
                onLoading = ::visibleProgress
        )

        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.wheel_action -> {
                wheelFilter = !wheelFilter
                item.isChecked = wheelFilter
            }
            R.id.wash_action -> {
                washFilter = !washFilter
                item.isChecked = washFilter
            }
            R.id.service_action -> {
                serviceFilter = !serviceFilter
                item.isChecked = serviceFilter
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        viewModel.updateProfile()
    }

    private fun visibleProgress(show: Boolean) {
//        refreshLayout.isRefreshing = show
    }

    private fun showProfile() {
        cl_main.visibility = View.VISIBLE
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }
}
