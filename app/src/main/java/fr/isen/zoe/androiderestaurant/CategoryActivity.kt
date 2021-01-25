package fr.isen.zoe.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.zoe.androiderestaurant.databinding.ActivityCategoryBinding

private lateinit var binding: ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleCategory.text = intent.getStringExtra(HomeActivity.CATEGORY)

    }
}