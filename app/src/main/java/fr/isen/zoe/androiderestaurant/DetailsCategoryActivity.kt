package fr.isen.zoe.androiderestaurant

import APIservices.APIdish
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.isen.zoe.androiderestaurant.databinding.ActivityDetailsCategoryBinding
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityDetailsCategoryBinding

class DetailsCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_category)

        binding = ActivityDetailsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Afficher le titre
        val dish = intent.getSerializableExtra("dish") as APIdish
        binding.titleDish.text = dish.title

        //Afficher les ingrédients
        binding.ingredientDetailsDish.text = dish.ingredients.map{ it.name }.joinToString(", ")

        //Afficher une photo
        val image = dish.getImage()
        if ( image != null && image.isNotEmpty()){
            Picasso.get()
                .load(image)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(binding.imageDish)
        }

        //Afficher le carrousel d'images
        //Si pas d'image alors pas de carrousel
        dish.getAllPictures()?.let {
           binding.viewPagerCarousel.adapter = CarouselAdapter(this, it)
        }

    }

}