package example.com.plugins

import example.com.data.run.RunDataSource
import example.com.data.user.UserDataSource
import example.com.routes.*
import example.com.security.hashing.HashingService
import example.com.security.token.TokenConfig
import example.com.security.token.TokenService
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting(
    userDataSource: UserDataSource,
    runDataSource: RunDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        staticFiles("/uploads", File("uploads"))

        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
        createRun(runDataSource)
    }
}
