package fr.isen.zoe.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.zoe.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.zoe.androiderestaurant.databinding.ActivityLogInBinding

private lateinit var binding: ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bouton aller à l'activité Register
        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}