package com.moichsan.consumerapp.base

import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {
    fun setupToolbar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.title = title
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
    }
}