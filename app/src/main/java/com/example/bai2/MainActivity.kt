package com.example.bai2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

data class Student(val name: String, val mssv: String)

class MainActivity : AppCompatActivity() {

    private lateinit var search: EditText
    private lateinit var listView: ListView
    private lateinit var searchIcon: ImageView
    private lateinit var adapter: ArrayAdapter<String>
    private val studentList = listOf(
        Student("Luyen Hoang Anh", "20210001"),
        Student("Tran Cong Minh", "20210002"),
        Student("Nguyen Hoang Thai Duong", "20210003"),
        Student("Le Phuong Thao", "20210004"),
        Student("Nguyen Bui Kieu Trang", "20210005")
    )
    private val displayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchIcon = findViewById(R.id.searchIcon)
        search = findViewById(R.id.search)
        listView = findViewById(R.id.listView)

        searchIcon.setOnClickListener {
            searchIcon.visibility = View.GONE
            search.visibility = View.VISIBLE
            search.requestFocus()
        }

        displayList.addAll(studentList.map { "${it.name} - ${it.mssv}" })
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        listView.adapter = adapter

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                filterList(query)
            }
        })
    }

    private fun filterList(query: String) {
        displayList.clear()
        if (query.length > 2) {
            val filtered = studentList.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
            }
            displayList.addAll(filtered.map { "${it.name} - ${it.mssv}" })
        } else {
            displayList.addAll(studentList.map { "${it.name} - ${it.mssv}" })
        }

        adapter.notifyDataSetChanged()
    }
}
