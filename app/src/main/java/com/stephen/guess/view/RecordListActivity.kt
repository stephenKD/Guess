package com.stephen.guess.view

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stephen.guess.R
import com.stephen.guess.adapter.RecordAdapter
import com.stephen.guess.data.GameDatabase
import kotlinx.android.synthetic.main.activity_record_list.*

class RecordListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        AsyncTask.execute {
            val records = GameDatabase.getInstance(this)?.recordDao()?.getAll()
            records?.let {
                rv_record_list.layoutManager = LinearLayoutManager(this)
                rv_record_list.setHasFixedSize(true)
                rv_record_list.adapter = RecordAdapter(this, it)
            }
        }
    }
}