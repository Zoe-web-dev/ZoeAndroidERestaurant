package fr.isen.zoe.androiderestaurant

import APIservices.APIdish
import APIservices.JsonBasket
import APIservices.JsonItemBasket
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.zoe.androiderestaurant.databinding.ActivityDetailsCategoryBinding
import java.io.File

private lateinit var binding: ActivityDetailsCategoryBinding

class DetailsCategoryActivity : AppCompatActivity() {

    private var quantity = 0
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_category)

        binding = ActivityDetailsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get preferences
        sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)

        //Afficher le titre
        val dish = intent.getSerializableExtra("dish") as APIdish
        binding.titleDish.text = dish.title

        //Afficher les ingrédients
        binding.ingredientDetailsDish.text = dish.ingredients.map { it.name }.joinToString(", ")

/*
        //Afficher la première photo de l'api
        val image = dish.getImage()
        if ( image != null && image.isNotEmpty()){
            Picasso.get()
                .load(image)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(binding.imageDish)
        }
*/

        //Afficher le carrousel d'images
        //Si pas d'image alors pas de carrousel
        dish.getAllPictures()?.let {
            binding.viewPagerCarousel.adapter = CarouselAdapter(this, it)
        }

        //gestion du bouton moins
        binding.buttonMoins.setOnClickListener {
            if (quantity > 0) {
                quantity -= 1
            }
            binding.counterQuantity.text = quantity.toString()

            var totalPrice = dish.getPriceItem().toFloat() * quantity
            binding.buttonOrder.text = "Acheter pour " + totalPrice.toString() + "€"
        }

        //gestion du bouton plus
        binding.buttonPlus.setOnClickListener {
            if (quantity < 20) {
                quantity += 1
            }
            binding.counterQuantity.text = quantity.toString()
            //mettre a jour le prix total
            var totalPrice = dish.getPriceItem().toFloat() * quantity
            binding.buttonOrder.text = "Acheter pour " + totalPrice.toString() + "€"
        }

        //gestion basket
        binding.buttonOrder.setOnClickListener {
            addToBasket(quantity,dish)
        }
    }

    //fonction ajouter au panier les dish selectionnés par l'utilisateur
    fun addToBasket(quantity: Int, item: APIdish) {
        val file = File(cacheDir.absolutePath + "/$BASKET_FILE")
        if (file.exists()) {
            val basket = Gson().fromJson(file.readText(), JsonBasket::class.java)
            basket.items.firstOrNull { it.item == item }?.let {
                it.quantity += quantity
            } ?: run {
                basket.items.add(JsonItemBasket(quantity, item))
            }
            saveInMemory(basket, file)
        } else {
            val basket = JsonBasket(mutableListOf(JsonItemBasket(quantity, item)))
            saveInMemory(basket, file)
        }
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