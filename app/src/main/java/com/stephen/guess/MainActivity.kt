package com.stephen.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    var secretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret:" + secretNumber.secret)
    }

    fun replay(view: View) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.string_replay))
            .setMessage(getString(R.string.string_are_you_sure))
            .setPositiveButton(getString(R.string.string_ok)) { dialog, which ->
                secretNumber.reset()
                tv_count.text = secretNumber.count.toString()
                ed_number.setText("")
            }
            .setNeutralButton(getString(R.string.string_cancel), null)
            .show()
    }

    fun check(view: View) {
        val n = ed_number.text.toString().let { "0" }.toInt()
        Log.d(TAG, "number: $n")
        val diff = secretNumber.validate(n)
        var message = getString(R.string.string_yes_you_got_it)
        if (diff < 0) {
            message = getString(R.string.string_bigger)
        } else if (diff > 0) {
            message = getString(R.string.string_smaller)
        }
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.string_dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.string_ok), null)
            .show()

        tv_count.text = secretNumber.count.toString()

    }
}