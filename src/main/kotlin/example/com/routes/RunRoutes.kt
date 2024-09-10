package example.com.routes

import example.com.data.run.RunDataSource
import example.com.data.run.request.CreateRunRequest
import example.com.data.run.toCreateRunResponse
import example.com.data.run.toRun
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

fun Route.createRun(
    runDataSource: RunDataSource
) {
    authenticate {
        post("run") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class) ?: kotlin.run {
                call.respond(HttpStatusCode.Unauthorized)
                return@post
            }

            val multipart = call.receiveMultipart()
            var createRunRequest: CreateRunRequest? = null
            var imageName: String? = null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        if (part.name == "RUN_DATA") {
                            createRunRequest = Json.decodeFromString(part.value)
                        }
                    }

                    is PartData.FileItem -> {
                        if (part.name == "MAP_PICTURE") {
                            imageName = part.save("uploads/")
                        }
                    }

                    else -> Unit
                }
                part.dispose()
            }

            val imageUrl = "127.0.0.1:8080/uploads/$imageName"
            val run = createRunRequest!!.toRun(userId, imageUrl)

            runDataSource.insertRun(run)

            call.respond(
                status = HttpStatusCode.OK,
                run.toCreateRunResponse()
            )
        }
    }
}

fun PartData.FileItem.save(path: String): String {
    val fileBytes = streamProvider().readBytes()
    val fileExtension = originalFileName?.takeLastWhile { it != '.' }
    val fileName = UUID.randomUUID().toString() + ".$fileExtension"
    val folder = File(path)
    folder.mkdir()
    File("$path$fileName").writeBytes(fileBytes)
    return fileName
}