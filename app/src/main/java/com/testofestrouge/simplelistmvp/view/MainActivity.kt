package com.testofestrouge.simplelistmvp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testofestrouge.simplelistmvp.R
import com.testofestrouge.simplelistmvp.adapter.CityAdapter
import com.testofestrouge.simplelistmvp.model.CityDataModel

class MainActivity : AppCompatActivity(), MainActivityInterface.View {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    private var presenterMain: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        presenterMain = MainActivityPresenter(this)
        presenterMain!!.setDataToListView()

    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CityAdapter()
        recyclerView.adapter = adapter
    }

    override fun setDataToListView(listCity: List<CityDataModel>) {
        adapter.setCityList(listCity)
    }
}