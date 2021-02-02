package APIservices

import java.io.Serializable

data class ResponseJSON(
    val data: ResponseJSONData,
    val code: String
) : Serializable

data class ResponseJSONData(
    val id: Int,
    val code: String,
    val id_shop: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val phone: String,
    val address: String,
    val postal_code: String,
    val birth_date: String,
    val town: String,
    val points: Int,
    val token: String,
    val gcmtoken: String,
    val create_date: String,
    val update_date: String
) : Serializable
