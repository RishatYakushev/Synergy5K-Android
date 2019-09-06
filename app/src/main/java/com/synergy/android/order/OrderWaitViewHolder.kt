package com.synergy.android.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.synergy.android.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order_wait.*

class OrderWaitViewHolder private constructor(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup): OrderWaitViewHolder =
            OrderWaitViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_order_wait, parent, false)
            )
    }

    fun bind(order: Order) {
        tv_title.text = order.title
        tv_time.text = order.time
        tv_car.text = order.car
        Glide.with(containerView.context)
            .load(R.drawable.card_order)
            .into(iv_photo)
    }
}