package example.com.data.run

import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MongoRunDataSource(
    db: CoroutineDatabase
) : RunDataSource {

    private val runs = db.getCollection<Run>()

    override suspend fun insertRun(run: Run): Boolean {
        return runs.insertOne(run).wasAcknowledged()
    }

    override suspend fun getRuns(id: String): List<Run> {
        return runs.find(Run::userId eq id).toList()
    }

    override suspend fun deleteRun(id: ObjectId): Boolean {
        return runs.deleteOne(Run::id eq id).wasAcknowledged()
    }
}