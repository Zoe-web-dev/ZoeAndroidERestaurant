package fr.isen.zoe.androiderestaurant

import APIservices.JsonBasket
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fr.isen.zoe.androiderestaurant.databinding.ActivityBasketDetailsBinding
import java.io.File

private lateinit var binding: ActivityBasketDetailsBinding

class BasketDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readFile()

        //gestion du bouton payer
        binding.buttonCommander.setOnClickListener {
            val sharedPreference =  getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
            val user = sharedPreference.getString("id_user","0")

            if(user == "0"){ //si pas connecter alors renvoie vers creation de compte
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            } else{
                deleteBasket()
                val toast = Toast.makeText(applicationContext, "Commande passée", Toast.LENGTH_SHORT)
                toast.show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
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

    private fun deleteBasket(){
        val file = File(cacheDir.absolutePath + "/$BASKET_FILE")
        file.delete()
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val BASKET_COUNT = "basket_count"
        const val BASKET_FILE = "basket.json"
    }
}