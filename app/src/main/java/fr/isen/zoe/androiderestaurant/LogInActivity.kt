package fr.isen.zoe.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import fr.isen.zoe.androiderestaurant.databinding.ActivityLogInBinding

private lateinit var binding: ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(BasketDetailsActivity.APP_PREFS, MODE_PRIVATE)
        //Appuyer pour creer un compte
        binding.buttonLogIn.setOnClickListener {
            if (isEverythingValid()) {
                logIn()
                val toast = Toast.makeText(applicationContext, "connecté", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        //Appuyer pour creation d'un compte
        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun isEverythingValid(): Boolean {
        return checkFillText() && isPasswordCorrect() && isMailCorrect();
    }

    fun checkFillText(): Boolean {
        val result = binding.emailText.text.isNotBlank()
                && binding.passwordText.text.isNotBlank()
        if (!result)
            Snackbar.make(binding.root, "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG)
                .show()
        return result
    }

    fun isMailCorrect(): Boolean {
        val result =
            !binding.emailText.text.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.emailText.text)
                .matches()
        if (!result)
            Snackbar.make(binding.root, "Email au format incorrect", Snackbar.LENGTH_LONG)
                .show()
        return result
    }

    fun isPasswordCorrect(): Boolean {
        val result = binding.passwordText.text.toString().length >= 8
        if (!result)
            Snackbar.make(binding.root, "Mot de passe doit contenir au moins 8 caractères", Snackbar.LENGTH_LONG)
                .show()
        return result;
    }

    //API Service
    private fun logIn() {

    }
}