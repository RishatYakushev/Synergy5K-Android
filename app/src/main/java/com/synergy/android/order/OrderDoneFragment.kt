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

class OrderDoneFragment : Fragment() {

    companion object {
        fun newInstance() = OrderDoneFragment()
    }

    private lateinit var viewModel: OrderDoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_done_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = true
            adapter = OrderDoneAdapter(
                listOf(
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    ),
                    Order(
                        "Шиномонтаж 5 колесо", "03.09.2019",
                        "Toyota Land Cruiser Prado", "А. Кутуя, д. 86/7"
                    )
                )
            )
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrderDoneViewModel::class.java)
    }

}
