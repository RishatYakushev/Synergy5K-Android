package com.synergy.android.order

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order_done.*


class OrderDoneViewHolder private constructor(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup): OrderDoneViewHolder =
            OrderDoneViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    com.synergy.android.R.layout.item_order_done,
                    parent,
                    false
                )
            )
    }

    fun bind(order: Order) {
        tv_title.text = order.title
        tv_time.text = order.time
        tv_address.text = order.address
        Glide.with(containerView.context)
            .asBitmap()
            .load(com.synergy.android.R.drawable.card_order)
            .optionalCenterCrop()
            .into(getRoundedImageTarget(containerView.context, iv_photo, 16F))
    }

    private fun getRoundedImageTarget(
        context: Context, imageView: ImageView,
        radius: Float
    ): BitmapImageViewTarget = object : BitmapImageViewTarget(imageView) {
        override fun setResource(resource: Bitmap?) {
            val circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(context.resources, resource)
            circularBitmapDrawable.cornerRadius = radius
            imageView.setImageDrawable(circularBitmapDrawable)
        }
    }
}