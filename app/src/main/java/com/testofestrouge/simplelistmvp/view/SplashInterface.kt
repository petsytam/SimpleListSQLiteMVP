package com.testofestrouge.simplelistmvp.view

interface SplashInterface {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun gotoMainActivity()
    }

    interface Presenter {
        fun loadDatabase()
    }
}