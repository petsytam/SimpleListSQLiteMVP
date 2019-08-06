package com.testofestrouge.simplelistmvp.view

import android.content.Context
import com.testofestrouge.simplelistmvp.constanst.Defines
import com.testofestrouge.simplelistmvp.model.DBQuery

class MainActivityPresenter(view: MainActivityInterface.View) : MainActivityInterface.Presenter{

    private var mainView = view
    private var model:DBQuery? = null

    override fun setDataToListView() {
        model = DBQuery(mainView as Context)
        val listCity = model!!.getListFromDatabse()
        mainView.setDataToListView(listCity)
    }

    override fun loadNextPage(nextPage: Int?) {
        if(nextPage != null) {
            model = DBQuery(mainView as Context)
            val listCity = model!!.getListCityPaging(Defines.PAGE_LIMIT,Defines.PAGE_LIMIT*nextPage)
            mainView.updateDataToList(listCity)
        }
    }

}