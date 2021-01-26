package fr.isen.zoe.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.zoe.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityCategoryBinding

class DetailsCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_category)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //afficher le titre du plat

    }

}