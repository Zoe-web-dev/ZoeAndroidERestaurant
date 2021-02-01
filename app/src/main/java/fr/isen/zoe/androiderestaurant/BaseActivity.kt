package fr.isen.zoe.androiderestaurant

import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.zoe.androiderestaurant.BasketDetailsActivity.Companion.APP_PREFS
import fr.isen.zoe.androiderestaurant.BasketDetailsActivity.Companion.BASKET_COUNT

open class BaseActivity: AppCompatActivity() {
    //Gestion du caddie
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_basket, menu);
        val menuView = menu?.findItem(R.id.basket)?.actionView
        val count = menuView?.findViewById<TextView>(R.id.totalItems)
        val sharedPrefrences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        count?.text = sharedPrefrences.getInt(BASKET_COUNT, 0).toString()

        menuView?.setOnClickListener{
            startActivity(Intent(this, BasketDetailsActivity::class.java))
        }
        return super.onCreateOptionsMenu(menu);
    }
}