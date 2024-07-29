package example.com

import example.com.data.user.MongoUserDataSource
import example.com.plugins.configureRouting
import example.com.plugins.configureSecurity
import example.com.plugins.configureSerialization
import example.com.security.hashing.SHA256HashingService
import example.com.security.token.JwtTokenService
import example.com.security.token.TokenConfig
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val dbName = "rocky-api"
    val db = KMongo.createClient().coroutine.getDatabase(dbName)
    val userDataSource = MongoUserDataSource(db)
    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.domain").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService = SHA256HashingService()

    configureSerialization()
    configureSecurity(tokenConfig)
    configureRouting(userDataSource, hashingService, tokenService, tokenConfig)
}
