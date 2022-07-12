package com.marvelapp.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.marvelapp.R

fun ImageView.loadImage(
    imageUrl: String?,
    @DrawableRes imageErrorRes: Int = R.drawable.ic_image_placeholder
) {
    val requestOptions = RequestOptions().apply {
        error(imageErrorRes)
        transform(CenterCrop())
    }
    Glide.with(this.context)
        .setDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .into(this)
}