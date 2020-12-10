package com.stephen.guess.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stephen.guess.R
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val num = intent.getStringExtra("VALUE") ?: "0"
        tv_number.text = num
        initListener()

    }

    private fun initListener() {
        btn_save.setOnClickListener {
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putString("name", ed_name.text.toString())
                .putString("number", tv_number.text.toString())
                .apply()
            val intent = Intent()
            intent.putExtra("NUMBER", tv_number.text.toString())
            intent.putExtra("NAME", ed_name.text.toString())

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}