package com.synergy.android.order

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OrderDoneAdapter(private val orders: List<Order>) :
    RecyclerView.Adapter<OrderDoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDoneViewHolder =
        OrderDoneViewHolder.create(parent)

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: OrderDoneViewHolder, position: Int) =
        holder.bind(orders[position])
}