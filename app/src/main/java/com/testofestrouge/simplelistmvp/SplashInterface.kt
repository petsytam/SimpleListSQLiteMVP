package com.testofestrouge.simplelistmvp

interface SplashInterface {

    interface View{
        fun showProgress()
        fun hideProgress()
        fun gotoMainActivity()
    }

    interface Presenter{
        fun loadDatabase()
    }
}