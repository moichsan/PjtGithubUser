package com.moichsan.consumerapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moichsan.consumerapp.R
import com.moichsan.consumerapp.data.Items
import com.moichsan.consumerapp.data.repository.NetworkState
import com.moichsan.consumerapp.ui.main.MainActivity.Companion.KEY_URL
import com.moichsan.consumerapp.ui.main.UserAdapter
import com.moichsan.consumerapp.util.Const.CATEGORY_FOLLOWERS
import com.moichsan.consumerapp.util.Const.CATEGORY_FOLLOWING
import com.moichsan.consumerapp.util.hide
import com.moichsan.consumerapp.util.show
import kotlinx.android.synthetic.main.fragment_child_detail.*
import kotlinx.android.synthetic.main.user_not_exist.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class ChildDetailFragment : Fragment(R.layout.fragment_child_detail) {
    private lateinit var viewModel: DetailViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()
        setupView()
    }

    private fun setupView() {
        userAdapter = UserAdapter {
            startActivity<DetailActivity>(KEY_URL to it)
        }
        with(rv_child) {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun getData() {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val items = activity?.intent?.getParcelableExtra<Items>(KEY_URL)
        when (arguments?.getInt(ARG_SECTION_NUMBER) ?: 1) {
            CATEGORY_FOLLOWING -> items?.let { it ->
                viewModel.getUsersFollowing(it.login.toString())
                    .observe(viewLifecycleOwner, Observer {
                        userAdapter.setResult(it)
                    })
            }
            CATEGORY_FOLLOWERS -> items?.let { it ->
                viewModel.getUsersFollowers(it.login.toString())
                    .observe(viewLifecycleOwner, Observer {
                        userAdapter.setResult(it)
                    })
            }
        }
        viewModel.networkStates.observe(viewLifecycleOwner, Observer {
            when (it) {
                NetworkState.LOADING -> pb_child.show()
                NetworkState.LOADED -> pb_child.hide()
                NetworkState.EMPTY -> {
                    when (arguments?.getInt(ARG_SECTION_NUMBER) ?: 1) {
                        CATEGORY_FOLLOWERS -> {
                            tv_user_not_exist.text =
                                (context?.resources?.getString(R.string.empty_followers))
                        }
                        CATEGORY_FOLLOWING -> {
                            tv_user_not_exist.text =
                                (context?.resources?.getString(R.string.empty_following))
                        }
                    }
                    rl_user_not_exist.show()
                    pb_child.hide()
                }
                NetworkState.ERROR -> {
                    pb_child.hide()
                    toast(NetworkState.ERROR.message)
                }
            }
        })
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): ChildDetailFragment {
            return ChildDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

}