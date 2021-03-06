package APIservices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class APIdish(
    @SerializedName("name_fr") val title: String,
    @SerializedName("ingredients") val ingredients: List<APIingredient>,
    @SerializedName("images") private val pictures: List<String>,
    @SerializedName("prices") private val prices: List<Price>
) : Serializable {
    fun getPrice() = prices[0].price + "€"
    fun getPriceItem() = prices[0].price
    fun getFormattedPrice() = prices[0].price + "€"
    fun getImage() = if (pictures.isNotEmpty() && pictures[0].isNotEmpty()) {
        pictures[0]
    } else {
        null
    }

    fun getAllPictures() = if (pictures.isNotEmpty() && pictures.any { it.isNotEmpty() }) {
        pictures.filter { it.isNotEmpty() }
    } else {
        null
    }
}
