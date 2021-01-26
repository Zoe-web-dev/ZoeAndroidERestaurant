package fr.isen.zoe.androiderestaurant

import APIservices.APIdish
import APIservices.APIresults
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.zoe.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData(intent.getStringExtra("category") ?: "")

        binding.titleCategory.text = intent.getStringExtra(HomeActivity.CATEGORY)

    }


    //Requete POST avec Volley
    //fonction loadData permet d'aller récuperer les données de l'api
    private fun loadData(category: String) {
        val postUrl = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue = Volley.newRequestQueue(this)
        val postData = JSONObject()
        try {
            postData.put("id_shop", "1")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, postUrl, postData,
            {
                val gson: APIresults = Gson().fromJson(it.toString(), APIresults::class.java)

                val dishes = gson.data.firstOrNull { it.name == category }?.dishes
                if (dishes != null) {
                    displayCategories(dishes)
                } else {
                    Log.e("CategoryActivity", "Pas de categorie trouvée")
                }
            },
            { error -> error.printStackTrace() }) //afficher erreur au lieu loader

        requestQueue.add(jsonObjectRequest)
    }

    //clicker sur la cardview pour aller a l'activité suivante
    private fun displayCategories(menu: List<APIdish>) {
        binding.categoryLoader.isVisible = false
        binding.listCategory.isVisible = true
        binding.listCategory.layoutManager = LinearLayoutManager(this)
        binding.listCategory.adapter = CategoryListAdapter(menu) {
            val intent = Intent(this, DetailsCategoryActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }
    }

}