package com.testofestrouge.simplelistmvp

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask

class SplashPresenter(view: SplashInterface.View) : SplashInterface.Presenter{

    private var splashView = view
    private var database: DBQuery? = null

    override fun loadDatabase() {
        LoadData().execute()
    }

    @SuppressLint("StaticFieldLeak")
    inner class LoadData(): AsyncTask<Any,Any,Boolean>(){

        override fun doInBackground(vararg p0: Any?): Boolean {

            database = DBQuery(splashView as Context)
            val isCoped = database!!.isDatabaseCreated()
            return isCoped
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)
            if (result)
            {
                splashView.hideProgress()
            }
            else
            {
                splashView.showProgress()
            }

            splashView.gotoMainActivity()

        }
    }

}