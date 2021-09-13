package com.svkylmz.showcountries.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.svkylmz.showcountries.R

fun ImageView.downloadImageFromUrl(url: String?) {
    val options = RequestOptions()
        .error(R.drawable.ic_launcher_foreground)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

@BindingAdapter("android:downloadUrl")   //to run this function on XML
fun downloadImage(view: ImageView, url: String?) {
    view.downloadImageFromUrl(url)
}