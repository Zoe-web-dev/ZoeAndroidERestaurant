package APIservices

import java.io.Serializable

data class RegisterJson(
    val id_shop: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    val email: String,
    val password: String
) : Serializable