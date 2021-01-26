package APIservices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class APIingredient (@SerializedName("name_fr") val name: String): Serializable
