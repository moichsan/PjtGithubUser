package com.moichsan.githubusers.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moichsan.githubusers.R
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.util.inflate
import com.moichsan.githubusers.util.load
import kotlinx.android.synthetic.main.users_item.view.*

class UsersAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val results = ArrayList<Items>()

    fun setResult(list: List<Items>) {
        results.clear()
        this.results.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Items?, listener: (String) -> Unit) = with(itemView) {
            ig_users.load(item?.avatar)
            tv_nickname.text = item?.login
            setOnClickListener { listener(item?.login ?: "nickname") }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder =
        ViewHolder(parent.inflate(R.layout.users_item))

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) =
        holder.bind(results[position], listener)
}
