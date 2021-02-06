package fr.isen.zoe.androiderestaurant

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import fr.isen.zoe.androiderestaurant.databinding.ActivityBasketDetailsBinding
import fr.isen.zoe.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bouton aller à l'activité category entrees
        binding.buttonEntrance.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            val toast = Toast.makeText(applicationContext, "Nos entrées", Toast.LENGTH_SHORT)
            toast.show()
            //envoyer une string dans une autre activité
            intent.putExtra(CATEGORY, getString(R.string.entrance))
            startActivity(intent)
            //finish()
        }

        //bouton aller à l'activité category plats
        binding.buttonDishes.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            val toast = Toast.makeText(applicationContext, "Nos plats", Toast.LENGTH_SHORT)
            toast.show()
            //envoyer une string dans une autre activité
            intent.putExtra(CATEGORY, getString(R.string.dishes))
            startActivity(intent)
        }

        //bouton aller à l'activité category deserts
        binding.buttonDeserts.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            val toast = Toast.makeText(applicationContext, "Nos desserts", Toast.LENGTH_SHORT)
            toast.show()
            //envoyer une string dans une autre activité
            intent.putExtra(CATEGORY, getString(R.string.deserts))
            startActivity(intent)
        }

        //bouton PANIER vers activity_basket_details
        binding.imageViewBasket.setOnClickListener {
            val intent = Intent(this, BasketDetailsActivity::class.java)
            val toast = Toast.makeText(applicationContext, "Votre panier", Toast.LENGTH_SHORT)
            toast.show()
            startActivity(intent)
        }

        //alert dialog connexion USER
        binding.idUser.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val intent = Intent(this, LogInActivity::class.java)
            val sharedPreference =
                getSharedPreferences(BasketDetailsActivity.APP_PREFS, Context.MODE_PRIVATE)
            val user = sharedPreference.getString("id_user", "0")
            if (user == "0") { //si pas connecter alors renvoie vers se log in
                builder.setMessage("Voulez vous vous connecter ?")
                builder.setPositiveButton("Oui", { dialogInterface: DialogInterface, i: Int -> startActivity(intent) })
                builder.setNegativeButton("Non", { dialogInterface: DialogInterface, i: Int -> })
                builder.show()
            } else {
                builder.setMessage("Voulez vous vous deconnecter ?")
                builder.setPositiveButton("Oui") { dialogInterface: DialogInterface, i: Int -> disconnectCount() }
                builder.setNegativeButton("Non") { dialogInterface: DialogInterface, i: Int -> }
                builder.show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(ACTIVITY, "destroyed") //log le cycle a détruire
    }

    private fun disconnectCount() {
        val sharedPreference =
            getSharedPreferences(BasketDetailsActivity.APP_PREFS, Context.MODE_PRIVATE)
        sharedPreference.edit().putString("id_user", "0").apply()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    //variable "globale"
    companion object {
        const val ACTIVITY = "HomeActivity"
        const val CATEGORY = "category"
    }
}