package com.synergy.android.order

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OrderWaitAdapter(private val orders: List<Order>) :
    RecyclerView.Adapter<OrderWaitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderWaitViewHolder =
        OrderWaitViewHolder.create(parent)

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: OrderWaitViewHolder, position: Int) =
        holder.bind(orders[position])
}
