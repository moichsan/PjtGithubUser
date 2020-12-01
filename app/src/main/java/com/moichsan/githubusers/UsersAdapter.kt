package com.moichsan.githubusers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class UsersAdapter(private val context: Context, private val listUsers: ArrayList<GithubUser>) :
    BaseAdapter() {
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View? {
        var itemView = view
        if (itemView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.users_item, viewGroup, false)
        }

        val viewHolder = itemView?.let { ViewHolder(it) }
        val user = getItem(position) as GithubUser
        viewHolder?.bind(user)

        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val txUsername: TextView = view.findViewById(R.id.tx_username)
        private val txName: TextView = view.findViewById(R.id.tx_name)
        private val igPhoto: ImageView = view.findViewById(R.id.ig_photo)

        internal fun bind(users: GithubUser) {
            txName.text = users.name
            txUsername.text = users.username
            users.photo?.let { igPhoto.setImageResource(it) }

        }
    }

    override fun getItem(pos: Int): Any {
        return listUsers[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()

    }

    override fun getCount(): Int {
        return listUsers.size
    }

}