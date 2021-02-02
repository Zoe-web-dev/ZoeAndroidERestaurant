package APIservices

import java.io.Serializable

data class LogInJson(
    val id_shop: Int,
    val email: String,
    val password: String
) : Serializable