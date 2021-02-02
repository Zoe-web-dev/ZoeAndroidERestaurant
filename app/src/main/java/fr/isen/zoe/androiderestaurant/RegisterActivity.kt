package fr.isen.zoe.androiderestaurant

import APIservices.RegisterJson
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.zoe.androiderestaurant.databinding.ActivityRegisterBinding
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            if (isEverythingValid()) {
                createAccount()
                val toast = Toast.makeText(applicationContext, "Compte créé", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        //bouton aller à l'activité LogIn
        binding.logIn.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }


    fun isEverythingValid(): Boolean {
        return checkFillText() && isPasswordCorrect() && isMailCorrect();
    }

    fun checkFillText(): Boolean {
        val result =
            binding.firstNameText.text.isNotBlank()
                    && binding.lastNameText.text.isNotBlank()
                    && binding.addressText.text.isNotBlank()
                    && binding.emailText.text.isNotBlank() && binding.passwordText.text.isNotBlank()
        if (!result)
            Snackbar.make(binding.root, "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG)
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

    fun isMailCorrect(): Boolean {
        val result =
            !binding.emailText.text.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.emailText.text)
                .matches()
        if (!result)
            Snackbar.make(binding.root, "Email au format incorrect", Snackbar.LENGTH_LONG)
                .show()
        return result
    }

    //API Service
    private fun createAccount() {
        val requestQueue = Volley.newRequestQueue(this)
        val postUrl = "http://test.api.catering.bluecodegames.com/user/register"
        val postData = JSONObject()
        try {
            postData.put("id_shop", "1")
            postData.put("firstname", binding.firstNameText.text)
            postData.put("lastname", binding.lastNameText.text)
            postData.put("email", binding.emailText.text)
            postData.put("address", binding.addressText.text)
            postData.put("password", binding.passwordText.text)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, postUrl, postData, {
                val gson = Gson().fromJson(it.toString(), RegisterJson::class.java)
            },
            {
                Log.i("state", "request failed")
            })
        requestQueue.add(jsonObjectRequest)
    }
}