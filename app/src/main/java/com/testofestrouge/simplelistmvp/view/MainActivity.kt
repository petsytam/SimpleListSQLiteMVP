package com.testofestrouge.simplelistmvp.view

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testofestrouge.simplelistmvp.R
import com.testofestrouge.simplelistmvp.adapter.CityAdapter
import com.testofestrouge.simplelistmvp.listener.EndlessScrollListener
import com.testofestrouge.simplelistmvp.model.CityDataModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainActivityInterface.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter
    private var listCity: MutableList<CityDataModel> = ArrayList()

    private var presenterMain: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        presenterMain = MainActivityPresenter(this)
        presenterMain!!.setDataToListView()

    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = linearLayoutManager
        adapter = CityAdapter()
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object: EndlessScrollListener(linearLayoutManager){
            override fun onLoadMore(currentPage: Int, totalItemCount: Int) {
                loadMore(currentPage)
            }

            override fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int) {

            }

        })
    }

    fun loadMore(page: Int){
        presenterMain!!.loadNextPage(page)
    }

    override fun setDataToListView(listCity: ArrayList<CityDataModel>) {
        this.listCity = listCity
        adapter.setCityList(listCity)
    }

    override fun updateDataToList(listCity: ArrayList<CityDataModel>) {
        this.listCity.addAll(listCity)
        Handler().postDelayed({
            recyclerView.adapter?.notifyDataSetChanged()
        },100)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_add -> {
                presenterMain!!.addMoreRecord()
            }
            R.id.action_show_last_record -> {
                presenterMain!!.showLastRecord()
            }
        }
        return true
    }

    override fun setProgress(progress: Int, max: Int) {
        tv_progress_status.text = "$progress of $max"
    }

    override fun showProgress() {
        progress_city.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        tv_progress_status.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_city.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        tv_progress_status.visibility = View.GONE
    }
}