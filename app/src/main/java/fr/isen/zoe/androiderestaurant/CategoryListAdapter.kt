package fr.isen.zoe.androiderestaurant

import APIservices.APIdish
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.zoe.androiderestaurant.databinding.CategoryCellBinding

class CategoryListAdapter(val categories: List<APIdish>, private val categoriesClickListener: (APIdish)->Unit) : RecyclerView.Adapter<CategoryListAdapter.CategoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val itemBinding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val myItem = categories[position]
        holder.title.text = categories[position].title
        holder.price.text = myItem.getFormattedPrice()

        val image = myItem.getImage()
        if ( image != null && image.isNotEmpty()){
            Picasso.get()
                .load(image)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(holder.image)
        }



        holder.layout.setOnClickListener{
            categoriesClickListener.invoke(categories[position])
        }
    }

    //determine le nombre d'items
    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryHolder(binding: CategoryCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.dishName
        val price = binding.priceDish
        val image = binding.imageItem
        val layout = binding.root
    }
}