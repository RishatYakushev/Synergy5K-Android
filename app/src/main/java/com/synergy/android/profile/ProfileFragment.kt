package com.synergy.android.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.synergy.android.R
import com.synergy.android.Router
import com.synergy.android.util.provideViewModel
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by kodein()

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by provideViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.profile_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tempUi()
    }

    private fun tempUi() {
        Glide.with(this)
            .load(R.drawable.avatar)
            .transition(withCrossFade())
            .optionalCircleCrop()
            .into(iv_avatar)
        tv_name.text = "Константин П."
        tv_phone.text = "+7 917 123 45 67"
        tv_point.text = "7 858"
        tv_discount.text = "10 %"
        tv_common_discount.text = "5 %"

        tv_logout.setOnClickListener {
            viewModel.logout()
            val router by kodein.instance<Router>()
            router.login(activity!!, clearStack = true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
