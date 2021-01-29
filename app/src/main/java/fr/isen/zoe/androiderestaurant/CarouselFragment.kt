package fr.isen.zoe.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.zoe.androiderestaurant.databinding.CategoryCarouselBinding

//pour avoir accès au contenu de la page xml
private lateinit var binding: CategoryCarouselBinding

class CarouselFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryCarouselBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString(ARG_OBJECT)?.let {
            Picasso.get().load(it).into(binding.imageCarousel)
        }
    }

    companion object {
        const val ARG_OBJECT = "object"
    }

}