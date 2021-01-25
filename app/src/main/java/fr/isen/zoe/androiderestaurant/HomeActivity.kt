package fr.isen.zoe.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.zoe.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding= ActivityHomeBinding.inflate(layoutInflater)
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

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(ACTIVITY,"destroyed") //log le cycle a détruire
    }

    //variable "globale"
    companion object {
        const val ACTIVITY = "HomeActivity"
        const val CATEGORY = "category"
    }
}