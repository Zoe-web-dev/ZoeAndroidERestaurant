package fr.isen.zoe.androiderestaurant

import APIservices.APIdish
import APIservices.JsonBasket
import APIservices.JsonItemBasket
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import fr.isen.zoe.androiderestaurant.databinding.ActivityDetailsCategoryBinding
import java.io.File

private lateinit var binding: ActivityDetailsCategoryBinding

class DetailsCategoryActivity : AppCompatActivity() {

    private var quantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_category)

        binding = ActivityDetailsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

            var totalPrice = dish.getPriceItem().toFloat()*quantity
            binding.buttonOrder.text = "Acheter pour " + totalPrice.toString() + "€"
        }

        //gestion du bouton plus
        binding.buttonPlus.setOnClickListener {
            if (quantity < 20) {
                quantity += 1
            }
            binding.counterQuantity.text = quantity.toString()
            //mettre a jour le prix total
            var totalPrice = dish.getPriceItem().toFloat()*quantity
            binding.buttonOrder.text = "Acheter pour " + totalPrice.toString() + "€"
        }

        //gestion basket
        binding.buttonOrder.setOnClickListener{
            addToBasket(dish, quantity)
        }
    }

    //fonction ajouter au panier les dish selectionnés par l'utilisateur
    fun addToBasket(item: APIdish, nbToAdd: Int) {
        val article = JsonItemBasket(nbToAdd, item)
        val file = File(cacheDir.absolutePath + "Basket.json")
        if(file.exists()){
            val json = Gson().fromJson(file.readText(), JsonBasket::class.java)
            json.totalQuantity += nbToAdd
            json.basket += article
            file.writeText(Gson().toJson(json))
        }else {
            val jsonObject = Gson().toJson(JsonBasket(nbToAdd, listOf(article)))
            file.writeText(jsonObject)
        }
    }
}