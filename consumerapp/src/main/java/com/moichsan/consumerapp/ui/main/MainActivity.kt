package com.moichsan.consumerapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moichsan.consumerapp.R
import com.moichsan.consumerapp.base.BaseActivity
import com.moichsan.consumerapp.ui.detail.DetailActivity
import com.moichsan.consumerapp.util.hide
import com.moichsan.consumerapp.util.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_not_exist.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbar, resources.getString(R.string.app_name))
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getData()
        setupView()
    }

    private fun setupView() {
        userAdapter = UserAdapter {
            startActivity<DetailActivity>(KEY_URL to it)
        }
        rv_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun getData() {
        viewModel.getAllLocalData(this).observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                Log.i("sts", it[0].login.toString())
                rl_user_not_exist.hide()
            } else {
                Log.i("sts", "data kosong")
                rl_user_not_exist.show()
                tv_user_not_exist.text = resources.getString(R.string.empty_followers)
            }
            userAdapter.setResult(it)
        })
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    companion object {
        const val KEY_URL = "key_url"
    }
}