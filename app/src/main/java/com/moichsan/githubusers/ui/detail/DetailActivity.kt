package com.moichsan.githubusers.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moichsan.githubusers.R
import com.moichsan.githubusers.base.BaseActivity
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.data.repository.NetState
import com.moichsan.githubusers.ui.home.MainActivity.Companion.KEY_URL
import com.moichsan.githubusers.util.Const.CT_FOLLOWERS
import com.moichsan.githubusers.util.Const.CT_FOLLOWING
import com.moichsan.githubusers.util.load
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : BaseActivity() {

    private lateinit var viewModel: DetailFollViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupToolbar(aw_toolbar, resources.getString(R.string.toolbar_detail))

        setupViewPager()
        getData()
        observeViewModel()


    }

    private fun setupViewPager() {
        val fragment = listOf<Fragment>(
            ChildFollDetailFragment.newInstance(CT_FOLLOWING),
            ChildFollDetailFragment.newInstance(CT_FOLLOWERS)
        )
        val adapter = ViewPager(this, fragment, supportFragmentManager)
        vp_foll.adapter = adapter
        vp_foll.offscreenPageLimit = 2
        tl_foll.setupWithViewPager(vp_foll)
    }

    private fun observeViewModel() {
        with(viewModel) {
            onDetail.observe(this@DetailActivity, Observer {
                setUI(it)
            })
            netState.observe(this@DetailActivity, Observer {
                when (it) {
                    NetState.ERROR -> {
                        toast(NetState.ERROR.message)
                    }
                }
            })
        }
    }

    private fun getData() {
        val urlUser = intent.getStringExtra(KEY_URL)
        viewModel = ViewModelProvider(this).get(DetailFollViewModel::class.java)
        urlUser?.let { viewModel.getDetailUser(it) }
        urlUser?.let { viewModel.getUserFollwing(it) }
        urlUser?.let { viewModel.getUserFollwers(it) }
    }

    private fun setUI(items: Items?) {
        ig_photo.load(items?.avatar)
        tv_username.text = items?.login
        tv_following.text = items?.following
        tv_follower.text = items?.followers


    }
}
