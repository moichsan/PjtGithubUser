package com.moichsan.githubusers

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowings: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var users: ArrayList<GithubUser>
    private lateinit var adapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepare()
        adapter = UsersAdapter(this, addItem())
        lv_list.adapter = adapter
        lv_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val listDataUser = GithubUser(0, "", "", "", "", "", "", "")
            listDataUser.username = dataUsername[position]
            listDataUser.photo = dataPhoto.getResourceId(position, position)
            listDataUser.name = dataName[position]
            listDataUser.company = dataCompany[position]
            listDataUser.followers = dataFollowers[position]
            listDataUser.following = dataFollowings[position]
            listDataUser.location = dataLocation[position]
            listDataUser.repository = dataRepository[position]

            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, listDataUser)
            startActivity(intent)
        }

    }

    private fun prepare() {
        dataUsername = resources.getStringArray(R.array.username)
        dataName = resources.getStringArray(R.array.name)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowings = resources.getStringArray(R.array.following)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
    }

    private fun addItem(): ArrayList<GithubUser> {
        users = ArrayList()
        for (pos in dataUsername.indices) {
            val user = GithubUser()
            user.photo = dataPhoto.getResourceId(pos, -1)
            user.name = dataName[pos]
            user.username = dataUsername[pos]
            users.add(user)

        }
        return users
    }
}
