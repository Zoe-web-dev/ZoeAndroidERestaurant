package fr.isen.zoe.androiderestaurant

import APIservices.JsonItemBasket
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.zoe.androiderestaurant.databinding.ActivityBasketDetailsBinding
import fr.isen.zoe.androiderestaurant.databinding.BasketItemCellBinding
import fr.isen.zoe.androiderestaurant.databinding.CategoryCellBinding

private lateinit var binding: BasketItemCellBinding

class BasketDetailsAdapter(
    private val data: MutableList<JsonItemBasket>,
    applicationContext: Context
) : RecyclerView.Adapter<BasketDetailsAdapter.BasketHolder>() {
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
        //on affiche le prix du dish
        holder.totalPrice.text = data.item.getPriceItem()
        //on affiche une image du plat
        val image = data.item.getImage()
        if (image != null && image.isNotEmpty()) {
            Picasso.get()
                .load(image)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class BasketHolder(binding: BasketItemCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.textItemBasket
        val layout = binding.root
        val totalPrice = binding.totalPrice
        val image = binding.imageViewItemBasket
    }
}

