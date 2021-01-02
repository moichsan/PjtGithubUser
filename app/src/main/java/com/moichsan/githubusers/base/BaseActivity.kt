package com.moichsan.githubusers.base

import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    fun setupToolbar(toolbar: Toolbar, title: String, showBack:Boolean) {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.title = title
            supportActionBar?.setDisplayShowHomeEnabled(showBack)
            supportActionBar?.setDisplayHomeAsUpEnabled(showBack)
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