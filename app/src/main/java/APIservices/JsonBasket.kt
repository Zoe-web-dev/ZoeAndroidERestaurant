package APIservices

import com.google.gson.annotations.SerializedName

class JsonBasket (@SerializedName("totalQuantity") var totalQuantity: Int, @SerializedName("basket") var basket: List<JsonItemBasket>){
}