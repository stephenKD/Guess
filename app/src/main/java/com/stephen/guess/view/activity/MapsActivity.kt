package com.stephen.guess.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stephen.guess.R
import com.stephen.guess.view.fragment.MapsFragment
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity() {
    companion object {
        const val MAPS_POSITION = "MAPS_POSITION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        vp2_maps.adapter = ViewPager2Adapter(this)
    }

    inner class ViewPager2Adapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 5
        override fun createFragment(position: Int): Fragment {
            val framgent = MapsFragment(position)
            framgent.arguments = Bundle().apply {
                putInt(MAPS_POSITION, position)
            }
            return framgent

//            return MapsFragment(position)
        }

    }
}