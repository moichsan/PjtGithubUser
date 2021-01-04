package com.moichsan.githubusers.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moichsan.githubusers.R
import com.moichsan.githubusers.base.BaseActivity
import com.moichsan.githubusers.ui.detail.DetailActivity
import com.moichsan.githubusers.ui.home.MainActivity
import com.moichsan.githubusers.ui.home.UsersAdapter
import com.moichsan.githubusers.util.hide
import com.moichsan.githubusers.util.show
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.user_not_exist.*

import org.jetbrains.anko.startActivity

class FavoriteActivity : BaseActivity() {
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var userAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        setupToolbar(toolbar, resources.getString(R.string.favorite), true)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        observeData()
        setupView()
    }

    private fun setupView() {
        userAdapter = UsersAdapter {
            startActivity<DetailActivity>(MainActivity.KEY_URL to it)
        }
        rv_favorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun observeData() {
        with(viewModel) {
            getAllLocalData(this@FavoriteActivity).observe(this@FavoriteActivity, Observer {
                if (!it.isNullOrEmpty()) {
                    Log.i("sts", it[0].login.toString())
                    rl_user_not_exist.hide()
                } else {
                    Log.i("sts", "kosong")
                    rl_user_not_exist.show()
                    tv_user_not_exist.text = resources.getString(R.string.empty_favorite)
                }
                userAdapter.setResult(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        observeData()
    }
}