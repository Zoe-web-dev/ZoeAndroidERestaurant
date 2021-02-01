package fr.isen.zoe.androiderestaurant

import APIservices.JsonBasket
import APIservices.JsonItemBasket
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.zoe.androiderestaurant.databinding.ActivityBasketDetailsBinding
import fr.isen.zoe.androiderestaurant.databinding.ActivityDetailsCategoryBinding
import java.io.File
import java.io.FileNotFoundException

private lateinit var binding: ActivityBasketDetailsBinding

class BasketDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readFile()
    }

    private fun readFile(){
        val gson = GsonBuilder().setPrettyPrinting().create()
        val file = File(cacheDir.absolutePath + "/${DetailsCategoryActivity.BASKET_FILE}")
        if (file.exists()){
            val basket = gson.fromJson(file.readText(), JsonBasket::class.java)
            val foodRecycler = binding.listItemsBasket
            foodRecycler.adapter = BasketDetailsAdapter(basket.items.toMutableList(),applicationContext)
            foodRecycler.layoutManager = LinearLayoutManager(this)
            foodRecycler.isVisible = true
        }
    }
}