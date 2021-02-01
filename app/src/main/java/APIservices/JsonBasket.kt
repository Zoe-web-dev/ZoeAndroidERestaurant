package APIservices

import com.google.gson.annotations.SerializedName

class JsonBasket (@SerializedName("items") var items: MutableList<JsonItemBasket>){
}