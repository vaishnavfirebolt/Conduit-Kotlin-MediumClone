package com.vaishnav.conduit.extensions

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImage(uri : String , circleCrop: Boolean = false){
    if(circleCrop)Glide.with(this).load(uri).into(this)
    else Glide.with(this).load(uri).circleCrop().into(this)
}

@SuppressLint("ConstantLocale")
val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

@SuppressLint("ConstantLocale")
val appDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

var TextView.timeStamp: String
    set(value) {
        val date = isoDateFormat.parse(value)!!
        text = appDateFormat.format(date)
    }
    get() {
        val date = appDateFormat.parse(text.toString())!!
        return isoDateFormat.format(date)
    }