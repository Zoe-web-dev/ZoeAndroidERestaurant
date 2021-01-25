package fr.isen.zoe.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.zoe.androiderestaurant.databinding.ActivityCategoryBinding
import java.util.*

private lateinit var binding: ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleCategory.text = intent.getStringExtra(HomeActivity.CATEGORY)

        binding.listCategory.layoutManager = LinearLayoutManager(this)
        binding.listCategory.adapter = CategoryListAdapter(listOf("gateau", "cookie","pompe"))
    }
}