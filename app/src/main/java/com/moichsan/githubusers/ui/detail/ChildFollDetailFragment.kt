package com.moichsan.githubusers.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moichsan.githubusers.R
import com.moichsan.githubusers.data.repository.NetState
import com.moichsan.githubusers.ui.home.MainActivity.Companion.KEY_URL
import com.moichsan.githubusers.ui.home.UsersAdapter
import com.moichsan.githubusers.util.Const.CT_FOLLOWERS
import com.moichsan.githubusers.util.Const.CT_FOLLOWING
import com.moichsan.githubusers.util.hide
import com.moichsan.githubusers.util.show
import kotlinx.android.synthetic.main.fragment_child_foll_detail.*
import kotlinx.android.synthetic.main.user_not_exist.*

import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class ChildFollDetailFragment : Fragment(R.layout.fragment_child_foll_detail) {

    private lateinit var viewModel: DetailFollViewModel
    private lateinit var userAdapter: UsersAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setView()
        getData()
        observeData()
    }

    private fun setView() {
        userAdapter = UsersAdapter {
            startActivity<DetailActivity>(KEY_URL to it)
        }
        rv_foll.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getData() {
        viewModel = ViewModelProvider(this).get(DetailFollViewModel::class.java)
        val urlUser = activity?.intent?.getStringExtra(KEY_URL)
        when (arguments?.getInt(ARG_SECTON_NUMBER) ?: 1) {
            CT_FOLLOWING -> urlUser?.let { viewModel.getUserFollwing(it) }
            CT_FOLLOWERS -> urlUser?.let { viewModel.getUserFollwers(it) }
        }
    }

    private fun observeData() {
        with(viewModel) {
            onFollowing.observe(viewLifecycleOwner, Observer {
                userAdapter.setResult(it)
            })
            onFollowers.observe(viewLifecycleOwner, Observer {
                userAdapter.setResult(it)
            })
            netState.observe(viewLifecycleOwner, Observer {
                when (it) {
                    NetState.LOADING -> pb_foll.show()
                    NetState.LOADED -> pb_foll.hide()
                    NetState.EMPTY -> {
                        when (arguments?. getInt(ARG_SECTON_NUMBER)?:1) {
                            CT_FOLLOWING -> {
                                tv_user_not_exist.text =
                                    (context?.resources?.getString(R.string.empty_followers))
                            }
                            CT_FOLLOWERS -> {
                                tv_user_not_exist.text =
                                    (context?.resources?.getString(R.string.empty_following))
                            }
                        }
                        rl_user_not_exist.show()
                        pb_foll.hide()
                    }
                    NetState.ERROR -> {
                        pb_foll.hide()
                        toast(NetState.ERROR.message)
                    }
                }
            })
        }
    }

    companion object {
        private const val ARG_SECTON_NUMBER = "secion_number"


        @JvmStatic
        fun newInstance(sectionNumber: Int): ChildFollDetailFragment {
            return ChildFollDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTON_NUMBER, sectionNumber)
                }
            }
        }
    }
}


