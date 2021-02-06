package fr.isen.zoe.androiderestaurant

import APIservices.UserJson
import APIservices.RegisterJson
import APIservices.ResponseJSON
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.zoe.androiderestaurant.databinding.ActivityLogInBinding
import org.json.JSONObject

private lateinit var binding: ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //pour se log
        binding.buttonLogIn.setOnClickListener {
            if (isEverythingValid()) {
                logIn()
                val intent = Intent(this, BasketDetailsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        //Appuyer pour creation d'un compte
        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
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
        val sharedPreferences = getSharedPreferences(BasketDetailsActivity.APP_PREFS, MODE_PRIVATE)
        val user = sharedPreferences.getString("id_user","0")
        if(user == "0"){ //si pas connecter alors renvoie vers creation de compte
            val toast = Toast.makeText(applicationContext, "Veuillez créer un compte", Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        } else{
            val toast = Toast.makeText(applicationContext, "Vous etes déjà connecté", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}

/*
if(user == "0"){
    val duration = Toast.LENGTH_LONG
    val toast = Toast.makeText(applicationContext,"Vous êtes connecté", duration)
    toast.show()
} else {
    val duration = Toast.LENGTH_LONG
    val toast = Toast.makeText(applicationContext,"Veuillez créer un compte", duration)
    toast.show()
    val intent = Intent(this, RegisterActivity::class.java)
    startActivity(intent)
}
*/