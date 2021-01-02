package com.moichsan.consumerapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moichsan.consumerapp.R
import com.moichsan.consumerapp.base.BaseActivity
import com.moichsan.consumerapp.data.Items
import com.moichsan.consumerapp.ui.main.MainActivity.Companion.KEY_URL
import com.moichsan.consumerapp.util.Const.CATEGORY_FOLLOWERS
import com.moichsan.consumerapp.util.Const.CATEGORY_FOLLOWING
import com.moichsan.consumerapp.util.load
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = true
    private lateinit var items: Items
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupToolbar(aw_toolbar, resources.getString(R.string.toolbar))
        getData()
        setupViewPager()
    }

    private fun setupViewPager() {
        val fragment = listOf<Fragment>(
            ChildDetailFragment.newInstance(CATEGORY_FOLLOWING),
            ChildDetailFragment.newInstance(CATEGORY_FOLLOWERS)
        )
        val adapter = ViewPagerAdapter(this, fragment, supportFragmentManager)
        vp_foll.adapter = adapter
        vp_foll.offscreenPageLimit = 2
        tl_foll.setupWithViewPager(vp_foll)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menuItem = menu
        menu?.getItem(0)?.isVisible = true
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                isFavorite = if (isFavorite) {
                    items.id?.let { viewModel.removeFavorite(it, this) }
                    false
                } else {
                    viewModel.addToFavorite(items, this)
                    true
                }
                setFavorite()
            }
        }
        return true
    }


    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }


    private fun getData() {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val mItems = intent.getParcelableExtra<Items>(KEY_URL)
        mItems?.let { items = it }
        ig_photo.load(items.avatar_url)
        tv_username.text = items.login
        tv_following.text = items.following.toString()
        tv_follower.text = items.followers.toString()
    }

}