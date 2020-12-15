package com.stephen.guess.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.stephen.guess.R
import com.stephen.guess.data.EventResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.coroutines.CoroutineContext

class SnookerActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    companion object {
        private val TAG = SnookerActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snooker)
        job = Job()
        launch {
            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
            val result = Gson().fromJson(data, EventResult::class.java)
            result.forEach {
                Log.d(TAG, "onCreate: $it")
            }
        }.start()

//        CoroutineScope(Dispatchers.IO).launch {
//            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
//            val result = Gson().fromJson(data, EventResult::class.java)
//            result.forEach {
//                Log.d(TAG, "onCreate: $it")
//            }
//        }
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}