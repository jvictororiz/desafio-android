package com.picpay.desafio.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.entities.User
import com.picpay.desafio.android.ui.viewHolders.UserListItemViewHolder
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {
    var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    var eventClick: ((user: User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position], eventClick)
    }

    override fun getItemCount(): Int = users.size

}