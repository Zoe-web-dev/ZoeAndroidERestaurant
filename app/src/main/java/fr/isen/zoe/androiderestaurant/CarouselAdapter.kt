package fr.isen.zoe.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import fr.isen.zoe.androiderestaurant.CarouselFragment.Companion.ARG_OBJECT
import android.os.Bundle as Bundle

class CarouselAdapter(activity: AppCompatActivity, private val list: List<String?>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        val fragment = CarouselFragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT, list[position])
        }
        return fragment
    }
}