package com.moichsan.githubusers.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moichsan.githubusers.R
import com.moichsan.githubusers.data.repository.NetState
import com.moichsan.githubusers.ui.detail.DetailActivity
import com.moichsan.githubusers.util.hide
import com.moichsan.githubusers.util.show
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_URL = "key_url"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var usersAdapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSearch()
        setupView()
        observeViewModel()


    }

    private fun setupView() {
        usersAdapter = UsersAdapter {
            startActivity<DetailActivity>(KEY_URL to it)
        }
        rv_user.apply {
            layoutManager = LinearLayoutManager (this@MainActivity)
            adapter = usersAdapter
        }

    }

    private fun observeViewModel() {
        with(viewModel) {
            onSearchSuccess.observe(this@MainActivity, Observer {
                usersAdapter.setResult(it)
            })
            networkStates.observe(this@MainActivity, Observer {
                when (it) {
                    NetState.LOADING -> {
                        pb_search.show()
                    }
                    NetState.LOADED -> {
                        pb_search.hide()
                    }
                    NetState.ERROR -> {
                        toast(NetState.ERROR.message)
                        pb_search.hide()
                    }
                    NetState.EMPTY -> {
                        toast(NetState.EMPTY.message)
                        pb_search.hide()
                    }
                }
            })
        }
    }
        private fun setupSearch() {

            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

            sc_view.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String?): Boolean {
                    if (s != "") {
                        s?.let { viewModel.getSearchUser(it) }
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean = false

            })
        }
    }

