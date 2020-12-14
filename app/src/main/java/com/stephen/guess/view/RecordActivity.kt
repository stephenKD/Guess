package com.stephen.guess.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stephen.guess.R
import com.stephen.guess.data.GameDatabase
import com.stephen.guess.data.Record
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val num = intent.getStringExtra("VALUE") ?: "0"
        tv_count.text = num
        initListener()

    }

    private fun initListener() {
        btn_save.setOnClickListener {
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putString("name", ed_name.text.toString())
                .putString("count", tv_count.text.toString())
                .apply()
            val intent = Intent().apply {
                putExtra("COUNT", tv_count.text.toString())
                putExtra("NAME", ed_name.text.toString())
            }.also { intent ->
                setResult(RESULT_OK, intent)
            }

//            //Room insert test
//            val database = Room.databaseBuilder(this, GameDatabase::class.java, "game.db").build()
//            val record = Record(ed_name.text.toString(), tv_count.text.toString().toInt())
//            Thread { database.recordDao().insert(record) }.start()

            //Room insert test for Singleton
            Thread {
                GameDatabase.getInstance(this)?.recordDao()
                    ?.insert(Record(ed_name.text.toString(), tv_count.text.toString().toInt()))
            }.start()
            finish()
        }
    }
}