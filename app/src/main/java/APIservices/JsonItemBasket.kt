package APIservices

import com.google.gson.annotations.SerializedName

data class JsonItemBasket (@SerializedName("quantity") var quantity: Int, @SerializedName("item") var item: APIdish)