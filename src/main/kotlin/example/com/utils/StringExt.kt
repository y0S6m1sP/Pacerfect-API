package example.com.utils

import org.bson.types.ObjectId

fun String.toObjectId(): ObjectId {
    return ObjectId(this)
}

fun String.toLocalhostImageUrl(): String {
    return "http://localhost:8080/uploads/$this"
}