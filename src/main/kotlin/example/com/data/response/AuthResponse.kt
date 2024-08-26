package example.com.data.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val userId: String,
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpirationTimestamp: Long
)
