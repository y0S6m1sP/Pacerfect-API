package example.com

import example.com.plugins.*
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val dbName = "rocky-api"
    val db = KMongo.createClient().coroutine.getDatabase(dbName)
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()
}
