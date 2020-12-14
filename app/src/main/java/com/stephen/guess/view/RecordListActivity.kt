package com.stephen.guess.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stephen.guess.R
import com.stephen.guess.adapter.RecordAdapter
import com.stephen.guess.data.GameDatabase
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordListActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        job = Job()
        //get records
        launch {
            val records = GameDatabase.getInstance(this@RecordListActivity)?.recordDao()?.getAll()
            records?.let {
                rv_record_list.layoutManager = LinearLayoutManager(this@RecordListActivity)
                rv_record_list.setHasFixedSize(true)
                rv_record_list.adapter = RecordAdapter(this@RecordListActivity, it)
            }
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            val records = GameDatabase.getInstance(this@RecordListActivity)?.recordDao()?.getAll()
//            records?.let {
//                runOnUiThread{
//                    rv_record_list.layoutManager = LinearLayoutManager(this@RecordListActivity)
//                    rv_record_list.setHasFixedSize(true)
//                    rv_record_list.adapter = RecordAdapter(this@RecordListActivity, it)
//                }
//            }
//        }

//        AsyncTask.execute {
//            val records = GameDatabase.getInstance(this)?.recordDao()?.getAll()
//            records?.let {
//                rv_record_list.layoutManager = LinearLayoutManager(this)
//                rv_record_list.setHasFixedSize(true)
//                rv_record_list.adapter = RecordAdapter(this, it)
//            }
//        }
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}