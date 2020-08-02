package com.aqube.coroutineexample.view

import android.widget.ImageView
import com.aqube.coroutineexample.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(uri: String?) {
    Glide.with(this.context)
        .setDefaultRequestOptions(RequestOptions().error(R.mipmap.ic_launcher))
        .load(uri)
        .into(this)
}