package example.com.data.run

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Run(
    @BsonId val id: ObjectId = ObjectId(),
    val dateTimeUtc: String,
    val durationMillis: Long,
    val distanceMeters: Int,
    val lat: Double,
    val long: Double,
    val avgSpeedKmh: Double,
    val maxSpeedKmh: Double,
    val totalElevationMeters: Int,
    val mapPictureUrl: String?,
    val userId: String
)
