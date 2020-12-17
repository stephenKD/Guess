package com.stephen.guess.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stephen.guess.R
import com.stephen.guess.view.activity.MapsActivity.Companion.MAPS_POSITION
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment(private val position: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            tv_maps_position.text = it.getInt(MAPS_POSITION).toString()
        }

//        tv_maps_position.text = (position + 1).toString()
    }
}