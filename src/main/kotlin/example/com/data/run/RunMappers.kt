package example.com.data.run

import example.com.data.run.request.CreateRunRequest
import example.com.data.run.response.RunDto
import example.com.utils.toObjectId
import java.time.Instant
import java.time.ZoneId

fun CreateRunRequest.toRun(userId: String, mapPictureUrl: String): Run {
    return Run(
        id = id.toObjectId(),
        dateTimeUtc = Instant.ofEpochMilli(epochMillis).atZone(ZoneId.of("UTC")).toString(),
        durationMillis = durationMillis,
        distanceMeters = distanceMeters,
        lat = lat,
        long = long,
        avgSpeedKmh = avgSpeedKmh,
        maxSpeedKmh = maxSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        mapPictureUrl = mapPictureUrl,
        userId = userId
    )
}

fun Run.toRunDto(): RunDto {
    return RunDto(
        id = id.toHexString(),
        dateTimeUtc = dateTimeUtc.replace(Regex("\\[.*]"), ""),
        durationMillis = durationMillis,
        distanceMeters = distanceMeters,
        lat = lat,
        long = long,
        avgSpeedKmh = avgSpeedKmh,
        maxSpeedKmh = maxSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        mapPictureUrl = mapPictureUrl
    )
}