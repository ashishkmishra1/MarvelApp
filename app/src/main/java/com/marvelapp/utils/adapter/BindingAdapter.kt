package com.marvelapp.utils.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.marvelapp.R

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    val imageErrorRes: Int = R.drawable.ic_image_placeholder
    val requestOptions = RequestOptions().apply {
        error(imageErrorRes)
        transform(CenterCrop())
    }
    Glide.with(view.context)
        .setDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .into(view)
}