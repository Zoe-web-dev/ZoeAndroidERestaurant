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

        //bouton aller à l'activité suivante
        binding.buttonEntrance.setOnClickListener {
            val intent = Intent(this, EntranceActivity::class.java)
            finish()
            startActivity(intent)
        }


        val toast = Toast.makeText(applicationContext, "entrées", Toast.LENGTH_SHORT)
        toast.show()



    }
}