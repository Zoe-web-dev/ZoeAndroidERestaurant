package APIservices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserJson(
    @SerializedName("id_shop") val id_shop: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
) : Serializable
