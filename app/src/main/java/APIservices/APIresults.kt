package APIservices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class APIresults (@SerializedName("data") val data: List<APIcategory>) : Serializable
