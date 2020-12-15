package com.stephen.guess.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.stephen.guess.R
import com.stephen.guess.data.EventResult
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_function_view.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ListActivity : AppCompatActivity() {
    companion object {
        private val TAG = ListActivity::class.java.simpleName
    }

    val functions = listOf<String>(
        "Camera",
        "Guess game",
        "Record list",
        "Download coupons",
        "News",
        "Snooker",
        "Maps"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

//        URL("https://www.google.com.tw").openStream().bufferedReader()

        CoroutineScope(Dispatchers.IO).launch {
            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
            println(data)
            val result = Gson().fromJson(data, EventResult::class.java)
            result.forEach {
                Log.d(TAG, "onCreate: $it")
            }

//            val array = JSONArray(data)
//            for (i in 0 until array.length()) {
//                val obj = array.getJSONObject(i)
//                println(obj.getInt("ID"))
//            }
        }

//        Thread{
//            val data = URL("https://www.google.com.tw").readText()
//        }.start()

        //RecyclerView
        rv_game.layoutManager = LinearLayoutManager(this)
        rv_game.setHasFixedSize(true)
        rv_game.adapter = FunctionAdapter()

    }

    inner class FunctionAdapter() : RecyclerView.Adapter<FunctionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_function_view, parent, false)
            return FunctionHolder(view)
        }

        override fun onBindViewHolder(holder: FunctionHolder, position: Int) {
            holder.tvName.text = functions[position]
            holder.itemView.setOnClickListener { functionClicked(position) }
        }

        override fun getItemCount(): Int {
            return functions.size
        }

    }

    private fun functionClicked(position: Int) {
        when (position) {
            1 -> startActivity(Intent(this, MainActivity::class.java))
            2 -> startActivity(Intent(this, RecordListActivity::class.java))
            4 -> startActivity(Intent(this, NewsActivity::class.java))
            5 -> startActivity(Intent(this, SnookerActivity::class.java))
            else -> return
        }
    }

    class FunctionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.tv_name

    }

}