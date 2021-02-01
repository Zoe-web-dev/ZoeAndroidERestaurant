package fr.isen.zoe.androiderestaurant

import APIservices.APIdish
import APIservices.JsonItemBasket
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import fr.isen.zoe.androiderestaurant.databinding.ActivityBasketDetailsBinding
import fr.isen.zoe.androiderestaurant.databinding.BasketItemCellBinding
import fr.isen.zoe.androiderestaurant.databinding.CategoryCellBinding

private lateinit var binding: BasketItemCellBinding

//deleteItemClickListener est une fonction
class BasketDetailsAdapter(
    private val data: MutableList<JsonItemBasket>, private val deleteItemClickListener: (JsonItemBasket)->Unit) : RecyclerView.Adapter<BasketDetailsAdapter.BasketHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasketHolder {
        binding = BasketItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val data: JsonItemBasket = data[position]
        //on affiche le titre du dish
        holder.title.text = data.item.title
        //on affiche le prix total
        holder.totalPrice.text = (data.quantity * data.item.getFormattedPrice().toDouble()).toString()
        //on affiche la quantité totale
        holder.totalQty.text = data.quantity.toString()
        //on affiche une image du plat
        val image = data.item.getImage()
        if (image != null && image.isNotEmpty()) {
            Picasso.get()
                .load(image)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(holder.image)
        }

        //fonction supprimer un item
        holder.delete.setOnClickListener {
            //supprime l'item de l'affichage
            deleteItem(position)
            //supprime dans le fichier json
            deleteItemClickListener.invoke(data)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun deleteItem(position: Int) {
        if(data[position].quantity != 1){
            data[position].quantity--
        }else{
            data.removeAt(position)
        }
        notifyDataSetChanged()
    }



    class BasketHolder(binding: BasketItemCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.textItemBasket
        val totalQty = binding.quantity
        val layout = binding.root
        val totalPrice = binding.totalPrice
        val image = binding.imageViewItemBasket
        val delete = binding.buttonRemoveItemBasket
    }
}