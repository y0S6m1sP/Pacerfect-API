package example.com.data.user.request

import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenRequest(
    val userId: String,
    val refreshToken: String
)
