package com.testofestrouge.simplelistmvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testofestrouge.simplelistmvp.adapter.CityAdapter

class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var city: List<CityDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initView()

    }

    private fun initData()
    {
        city = DBQuery(this).getListFromDatabse()
    }

    private fun initView()
    {
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CityAdapter()
        recyclerView.adapter = adapter
        adapter.setCityList(city)
    }
}