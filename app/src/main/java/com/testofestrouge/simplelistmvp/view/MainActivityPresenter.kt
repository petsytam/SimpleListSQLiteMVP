package com.testofestrouge.simplelistmvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.testofestrouge.simplelistmvp.constanst.Defines
import com.testofestrouge.simplelistmvp.model.CityDataModel
import com.testofestrouge.simplelistmvp.model.DBQuery

class MainActivityPresenter(view: MainActivityInterface.View) : MainActivityInterface.Presenter{

    companion object{
        const val MAX_SIZE_UNIT_TEST = 272128
    }
    private var mainView = view
    private var model = DBQuery(mainView as Context)

    override fun setDataToListView() {
        val listCity = model.getListFirstFromDatabse()
        mainView.setDataToListView(listCity)
    }

    override fun loadNextPage(nextPage: Int?) {
        if(nextPage != null) {
            val listCity = model.getListCityPaging(Defines.PAGE_LIMIT,Defines.PAGE_LIMIT*nextPage)
            mainView.updateDataToList(listCity)
        }
    }

    override fun addMoreRecord() {
        mainView.showProgress()
        Unitest().execute()
    }

    override fun showLastRecord() {
        val listCity = model.getListLastFromDatabse()
        mainView.updateDataToList(listCity)
    }

    @SuppressLint("StaticFieldLeak")
    inner class Unitest: AsyncTask<Unit,Int,Boolean>(){

        override fun doInBackground(vararg p0: Unit?): Boolean {
            for (i in 0 until MAX_SIZE_UNIT_TEST)
            {
                model.addNewCity(CityDataModel("id_$i","country_$i","city_$i",i))
                publishProgress(i)
            }
            return true
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            if(values[0]!=null) {
                val progress = values[0] as Int
                mainView.setProgress(progress,MAX_SIZE_UNIT_TEST)
            }
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            mainView.hideProgress()
        }
    }

}