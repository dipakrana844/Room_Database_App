package com.executor.roomdatabaseapp.Activity

import android.content.Intent
import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.executor.roomdatabaseapp.Adapter.RecyclerViewAdapter
import com.executor.roomdatabaseapp.Database.Contact
import com.executor.roomdatabaseapp.Database.ContactDatabase
import com.executor.roomdatabaseapp.R
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ContactClickListener {

    //    lateinit var database: ContactDatabase
    private val TAG = "MainActivity"
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        database = ContactDatabase.getDatabase(this)

//        GlobalScope.launch {
//            database.contactDao().insertContact(Contact(0,
//                "Dipak", "Rana", Date(),
//                20, 777, Date()))
//        }
        rvContact.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter

        }
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllContactObservers().observe(this, androidx.lifecycle.Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        fabAddBtn.setOnClickListener {
            var intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDeleteUserClickListener(contact: Contact) {
        viewModel.deleteContactInfo(contact)
    }

    override fun onItemClickListener(contact: Contact) {
        etContactFirstName.setText(contact.fName)
        etContactLastName.setText(contact.lName)
        etContactNumber.setText(contact.phone)
    }

//    fun getData(view: View) {
//        database.contactDao().getContact().observe(this, androidx.lifecycle.Observer {
//            Log.d(TAG, "getData: $it")
//        })
//    }
}