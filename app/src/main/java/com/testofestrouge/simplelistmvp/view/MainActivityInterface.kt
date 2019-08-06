package com.testofestrouge.simplelistmvp.view

import com.testofestrouge.simplelistmvp.model.CityDataModel

interface MainActivityInterface {

    interface View {
        fun setDataToListView(listCity: List<CityDataModel>)
    }

    interface Presenter {
        fun setDataToListView()
    }
}