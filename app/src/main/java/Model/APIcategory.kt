package Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class APIcategory (@SerializedName("name_fr") val name: String, @SerializedName("items") val dishes: List<Dish>)

