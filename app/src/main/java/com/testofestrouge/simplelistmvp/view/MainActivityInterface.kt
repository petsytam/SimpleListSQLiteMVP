package com.testofestrouge.simplelistmvp.view

import com.testofestrouge.simplelistmvp.model.CityDataModel

interface MainActivityInterface {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setProgress(progress: Int,max: Int)
        fun setDataToListView(listCity: ArrayList<CityDataModel>)
        fun updateDataToList(listCity: ArrayList<CityDataModel>)
    }

    interface Presenter {
        fun setDataToListView()
        fun loadNextPage(nextPage: Int?)
        fun addMoreRecord()
    }
}