package com.testofestrouge.simplelistmvp.view

import android.content.Context
import com.testofestrouge.simplelistmvp.model.DBQuery

class MainActivityPresenter(view: MainActivityInterface.View) : MainActivityInterface.Presenter{

    private var mainView = view
    private var model:DBQuery? = null

    override fun setDataToListView() {
        model = DBQuery(mainView as Context)
        val listCity = model!!.getListFromDatabse()
        mainView.setDataToListView(listCity)
    }

}