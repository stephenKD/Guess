package com.stephen.guess.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stephen.guess.R
import com.stephen.guess.data.Record
import kotlinx.android.synthetic.main.item_record_view.view.*

class RecordAdapter(context: Context, var records: List<Record>) :
    RecyclerView.Adapter<RecordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_record_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.tvNickname.text = records[position].nickname
        holder.tvCount.text = records[position].counter.toString()
    }

    override fun getItemCount(): Int {
        return records.size
    }

}

class RecordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvNickname: TextView = view.tv_name
    val tvCount: TextView = view.tv_count

}