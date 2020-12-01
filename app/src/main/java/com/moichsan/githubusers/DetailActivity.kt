package com.moichsan.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        var EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailUser: GithubUser = intent.getParcelableExtra(EXTRA_DATA)

        ig_photo.setImageResource(detailUser.photo!!)
        tv_name.text = detailUser.name
        tv_username.text = detailUser.username
        tv_following.text = detailUser.following
        tv_followers.text = detailUser.followers
        tv_location.text = detailUser.location
        tv_company.text = detailUser.company
        tv_repository.text = detailUser.repository


    }
}
