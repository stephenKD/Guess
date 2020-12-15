package com.stephen.guess.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stephen.guess.GameResult
import com.stephen.guess.GuessViewModel
import com.stephen.guess.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var viewModel: GuessViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Log.d(TAG, "onCreate: ")
        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java)
        viewModel.counter.observe(this, Observer { data ->
            tv_count.text = data.toString()
        })
        viewModel.result.observe(this, { result ->
            val message = when (result) {
                GameResult.BIGGER -> getString(R.string.string_bigger)
                GameResult.SMALLER -> getString(R.string.string_smaller)
                GameResult.BINGO -> getString(R.string.string_yes_you_got_it)
            }
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.string_dialog_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.string_ok)) { dialog, which ->
                    if (result == GameResult.BINGO) {
                        val intent = Intent(this, RecordActivity::class.java)
                        intent.putExtra("VALUE", tv_count.text.toString())
                        startActivityForResult(intent, REQUEST_CODE)
                    }
                }
                .show()
        })

        val number = getSharedPreferences("guess", MODE_PRIVATE).getString("number", "0")
        val count = getSharedPreferences("guess", MODE_PRIVATE).getString("count", "NULL")
        Log.d(TAG, "secret get shard preferences: $number / $count")

        //Room read test
//        AsyncTask.execute {
//            val list = GameDatabase.getInstance(this)?.recordDao()?.getAll()
//            list?.forEach {
//                Log.d(TAG, "record: ${it.nickname} ${it.counter}")
//            }
//        }
//        //Room test
//        val database = Room.databaseBuilder(this, GameDatabase::class.java, "game.db").build()
//        val record = Record("Tom", 1)
//        Thread(){
//            database.recordDao().insert(record)
//        }.start()

    }

    fun replay(view: View) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.string_replay))
            .setMessage(getString(R.string.string_are_you_sure))
            .setPositiveButton(getString(R.string.string_ok)) { dialog, which ->
                viewModel.reset()
                ed_number.setText("")
            }
            .setNeutralButton(getString(R.string.string_cancel), null)
            .show()
    }

    fun check(view: View) {
        val num = if (TextUtils.isEmpty(ed_number.text.toString())) {
            0
        } else {
            ed_number.text.toString().toInt()
        }
        viewModel.guess(num)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d(
                    TAG,
                    "activity for result: ${data?.getStringExtra("COUNT")} / ${
                        data?.getStringExtra("NAME")
                    }"
                )
                btn_replay.callOnClick()
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart: ")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume: ")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause: ")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop: ")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d(TAG, "onRestart: ")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy: ")
//    }

}