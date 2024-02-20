package com.hrtsoft.kotlin

import CustomAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyid)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val data = ArrayList<ItemsViewModel>()
        data.add(ItemsViewModel(R.drawable.shoe, "$99 ", "Boat shoes"))
        data.add(ItemsViewModel(R.drawable.b, "$50 ", "Boxing Shoes"))
        data.add(ItemsViewModel(R.drawable.c, "$60 ", "Sneakers"))
        data.add(ItemsViewModel(R.drawable.d, "$70 ", "Uggs"))

        data.add(ItemsViewModel(R.drawable.shoe, "$101", "Asics"))
        data.add(ItemsViewModel(R.drawable.shoe, "$45", "Champion's"))
        data.add(ItemsViewModel(R.drawable.shoe, "$85", "underarmour"))

        adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

        setupSearchView()
    }

    private fun setupSearchView() {
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}
