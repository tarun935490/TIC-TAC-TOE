package com.example.demo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.ItemListBinding
import com.example.demo.userdata

class UserAdapter(private val context: Context, private val userlist: ArrayList<userdata>) :
    RecyclerView.Adapter<UserAdapter.viewHolder>() {
    class viewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = viewHolder(
        ItemListBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun getItemCount() = userlist.size
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.apply {
            textView6.text = userlist[position].textview
            checkbox.isChecked = userlist[position].isChecked
            listmain.setOnClickListener {
                for (i in 0..<userlist.size) {
                    userlist[position].isChecked = i == position
                    // userlist[position].isChecked = !userlist[position].isChecked
                }
                notifyDataSetChanged()

            }
        }
    }
}