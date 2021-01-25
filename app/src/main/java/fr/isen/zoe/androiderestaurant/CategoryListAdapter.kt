package fr.isen.zoe.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.zoe.androiderestaurant.databinding.CategoryCellBinding

class CategoryListAdapter(val categories: List<String>) : RecyclerView.Adapter<CategoryListAdapter.CategoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val itemBinding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.title.text = categories[position]
    }

    //determine le nombre d'items
    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryHolder(binding: CategoryCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.dishName
    }
}