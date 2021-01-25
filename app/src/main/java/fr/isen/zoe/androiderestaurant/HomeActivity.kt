package fr.isen.zoe.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //bouton aller à l'activité suivante
        buttonEntrance.setOnClickListener {
            val intent = Intent(this, EntranceActivity::class.java)
            finish()
            startActivity(intent)
        }


        val toast = Toast.makeText(applicationContext, "entrées", Toast.LENGTH_SHORT)
        toast.show()



    }
}