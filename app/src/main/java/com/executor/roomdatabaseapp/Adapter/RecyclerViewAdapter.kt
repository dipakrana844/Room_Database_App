package com.executor.roomdatabaseapp.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.executor.roomdatabaseapp.Activity.MainActivity
import com.executor.roomdatabaseapp.Database.Contact
import com.executor.roomdatabaseapp.R
import kotlinx.android.synthetic.main.row_contact.view.*

class RecyclerViewAdapter(private val listener: ContactClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var contact = ArrayList<Contact>()

    fun setListData(data: ArrayList<Contact>) {
        this.contact = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.row_contact, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(contact[position])
        }
        holder.bind(contact[position])
    }

    override fun getItemCount(): Int {
        return contact.size
    }

    class MyViewHolder(view: View, val listener: ContactClickListener) :
        RecyclerView.ViewHolder(view) {

        val tvName = view.tvContactName
        val tvAge = view.tvContactAge
        val tvNumber = view.tvContactNumber
        val deleteUserID = view.ibContactDeleteBtn

        @SuppressLint("SetTextI18n")
        fun bind(data: Contact) {
            tvName.text = data.fName + data.lName
            tvAge.text = data.dob.toString()
            tvNumber.text = data.phone.toString()
            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }

    }

    interface ContactClickListener {
        fun onDeleteUserClickListener(contact: Contact)
        fun onItemClickListener(contact: Contact)


    }
}