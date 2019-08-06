package com.testofestrouge.simplelistmvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.testofestrouge.simplelistmvp.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashInterface.View {

    private var presenter: SplashPresenter? = null

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        titleSplash.text = getString(R.string.app_name)
    }

    override fun gotoMainActivity() {
        Handler().postDelayed({
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
            finish()
        }, 1000)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        presenter = SplashPresenter(this)

        initData()
    }

    private fun initData() {
        presenter?.loadDatabase()
    }

}
