package fr.isen.zoe.androiderestaurant

import APIservices.JsonBasket
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fr.isen.zoe.androiderestaurant.databinding.ActivityBasketDetailsBinding
import java.io.File

private lateinit var binding: ActivityBasketDetailsBinding

class BasketDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readFile()
    }

    private fun readFile() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val file = File(cacheDir.absolutePath + "/${DetailsCategoryActivity.BASKET_FILE}")
        if (file.exists()) {
            val basket = gson.fromJson(file.readText(), JsonBasket::class.java)
            val foodRecycler = binding.listItemsBasket
            foodRecycler.adapter = BasketDetailsAdapter(basket.items.toMutableList()) {
                basket.items.remove(it)
                resetBasket(basket)
            }
            foodRecycler.layoutManager = LinearLayoutManager(this)
            foodRecycler.isVisible = true
        }
    }

    //fonction qui met à jour le fichier json (panier)
    fun resetBasket(basket: JsonBasket) {
        val file = File(cacheDir.absolutePath + "/$BASKET_FILE")
        //met a jour le nombre d'items dans le panier
        saveInMemory(basket, file)
    }

    private fun saveInMemory(basket: JsonBasket, file: File) {
        saveDishCount(basket)
        file.writeText(GsonBuilder().create().toJson(basket))
    }

    private fun saveDishCount(basket: JsonBasket) {
        val count = basket.items.sumOf { it.quantity }
        val sharedPreferences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        sharedPreferences.edit().putInt(BASKET_COUNT, count).apply()
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val BASKET_COUNT = "basket_count"
        const val BASKET_FILE = "basket.json"
    }
}