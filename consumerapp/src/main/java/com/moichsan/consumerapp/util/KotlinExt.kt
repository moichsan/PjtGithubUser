package com.moichsan.consumerapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.moichsan.consumerapp.R

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.load(url: Any?) =
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_person_outline_black_24dp)
        .error(R.drawable.ic_warning_black_24dp)
        .into(this)