package com.moichsan.consumerapp.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moichsan.consumerapp.R
import com.moichsan.consumerapp.data.Items
import com.moichsan.consumerapp.util.inflate
import com.moichsan.consumerapp.util.load
import kotlinx.android.synthetic.main.users_item.view.*

class UserAdapter(private val listener: (Items) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var results = ArrayList<Items>()

    fun setResult(list: List<Items>) {
        results.clear()
        this.results.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.users_item))

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(results[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Items?, listener: (Items) -> Unit) = with(itemView) {
            ig_users.load(item?.avatar_url)
            tv_nickname.text = item?.login
            setOnClickListener { item?.let { it1 -> listener(it1) } }
        }
    }

}