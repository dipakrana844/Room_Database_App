package com.executor.roomdatabaseapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.executor.roomdatabaseapp.Adapter.RecyclerViewAdapter
import com.executor.roomdatabaseapp.Database.Contact
import com.executor.roomdatabaseapp.R
import kotlinx.android.synthetic.main.activity_add.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity(),RecyclerViewAdapter.ContactClickListener {
    var msCalendarSelectedDateTime: Int? = null
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btnSave.setOnClickListener {
            val fname = etContactFirstName.text.toString()
            val lname = etContactLastName.text.toString()

            tvContactCalender.text = currentDateTime()
            msCalendarSelectedDateTime = tvContactCalender.inputType

            val number = etContactNumber.inputType

            if (btnSave.text.equals("Save")) {
                val contact =
                    Contact(0, fname, lname, msCalendarSelectedDateTime!!, number, Date())
                viewModel.insertContactInfo(contact)
            } else {
                val contact =
                    Contact(etContactFirstName.getTag(etContactFirstName.id).toString().toInt(),
                        fname,
                        lname,
                        msCalendarSelectedDateTime!!,
                        number,
                        Date())
                viewModel.updateContactInfo(contact)
                btnSave.setText("Save")
            }
            etContactFirstName.setText("")
            etContactLastName.setText("")
//            msCalendarSelectedDateTime
            etContactNumber.setText("")
        }
    }

    private fun currentDateTime(): String {
        val dateFormat: DateFormat =
            SimpleDateFormat("EEE" + ", " + "MMM dd" + ", " + "yyyy" + ", " + "h:mm a")
        val date = Date()
        return dateFormat.format(date)
    }

    override fun onDeleteUserClickListener(contact: Contact) {
        viewModel.deleteContactInfo(contact)
    }

    override fun onItemClickListener(contact: Contact) {
        etContactFirstName.setText(contact.fName)
        etContactLastName.setText(contact.lName)
        msCalendarSelectedDateTime
        etContactNumber.setText(contact.phone)
    }


}