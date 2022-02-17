package com.yodgorbek.newstask.domain.utils


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Picasso.get().load(url).into(view)
}