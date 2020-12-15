package com.stephen.guess.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stephen.guess.R
import com.stephen.guess.viewmodel.SnookerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
        val viewModel = ViewModelProvider(this).get(SnookerViewModel::class.java)
        viewModel.event.observe(this, Observer { events ->
            Log.d(TAG, "onCreate: ${events.size}")
        })

//        launch {
//            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
//            val result = Gson().fromJson(data, EventResult::class.java)
//            result.forEach {
//                Log.d(TAG, "onCreate: $it")
//            }
//        }

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