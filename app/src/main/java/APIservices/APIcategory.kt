package APIservices

import com.google.gson.annotations.SerializedName

data class APIcategory (@SerializedName("name_fr") val name: String, @SerializedName("items") val dishes: List<APIdish>)

