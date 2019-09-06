package com.synergy.android.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.synergy.android.R
import kotlinx.android.synthetic.main.order_wait_fragment.*

class OrderWaitFragment : Fragment() {

    companion object {
        fun newInstance() = OrderWaitFragment()
    }

    private lateinit var viewModel: OrderWaitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_wait_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = true
            adapter = OrderWaitAdapter(
                listOf(
                    Order(
                        "Шиномонтаж 5 колесо", "3.09.2019 в 15:00",
                        "Toyota Land Cruiser Prado", ""
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "3.09.2019 в 15:00",
                        "Toyota Land Cruiser Prado", ""
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "3.09.2019 в 15:00",
                        "Toyota Land Cruiser Prado", ""
                    )
                )
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrderWaitViewModel::class.java)
    }

}
