package example.com.data.run

import org.bson.types.ObjectId

interface RunDataSource {
    suspend fun insertRun(run: Run): Boolean

    suspend fun getRuns(id: String): List<Run>

    suspend fun deleteRun(id: ObjectId): Boolean
}