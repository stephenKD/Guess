package com.stephen.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var secretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "secret:" + secretNumber.secret)
    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
        Log.d("MainActivity", "number: $n")
        val diff = secretNumber.validate(n)
        var message = "Yes you got it"
        if (diff < 0) {
            message = "Bigger"
        } else if (diff > 0) {
            message = "Smaller"
        }
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}