package com.stephen.guess.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stephen.guess.R
import com.stephen.guess.view.fragment.NewsFragment

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.cl_container, NewsFragment.instance)
        fragmentTransaction.commit()
    }
}