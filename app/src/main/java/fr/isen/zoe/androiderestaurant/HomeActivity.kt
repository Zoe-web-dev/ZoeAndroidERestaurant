package fr.isen.zoe.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.zoe.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bouton aller à l'activité category entrees
        binding.buttonEntrance.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            val toast = Toast.makeText(applicationContext, "access entrance", Toast.LENGTH_SHORT)
            toast.show()
            startActivity(intent)
            //finish()
        }

        //bouton aller à l'activité category plats
        binding.buttonDishes.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            val toast = Toast.makeText(applicationContext, "access dishes", Toast.LENGTH_SHORT)
            toast.show()
            startActivity(intent)
        }

        //bouton aller à l'activité category deserts
        binding.buttonDeserts.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            val toast = Toast.makeText(applicationContext, "access deserts", Toast.LENGTH_SHORT)
            toast.show()
            startActivity(intent)
        }

    }
}