package com.stephen.guess.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stephen.guess.R
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_function_view.view.*

class ListActivity : AppCompatActivity() {
    val functions = listOf<String>(
        "Camera",
        "Guess game",
        "Record list",
        "Download coupons",
        "News",
        "Maps"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

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
            else -> return
        }
    }

    class FunctionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.tv_name

    }

}