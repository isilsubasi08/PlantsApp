package com.isilsubasi.plantsapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.isilsubasi.plantsapp.R

fun ImageView.loadImage(url : String){
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.internet)
        .centerCrop()
        .into(this)

}