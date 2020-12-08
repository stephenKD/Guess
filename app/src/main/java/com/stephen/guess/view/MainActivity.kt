package com.stephen.guess.view

import android.os.Bundle
import android.text.TextUtils
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
    private lateinit var viewModel: GuessViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                .setPositiveButton(getString(R.string.string_ok), null)
                .show()
        })
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
}