package com.moichsan.githubusers.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    fun setupToolbar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        if(supportActionBar !=null) {
            supportActionBar?.title =title
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener{
                onBackPressed()
            }   

        }
    }
}