package example.com.data.run

import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoRunDataSource(
    db: CoroutineDatabase
) : RunDataSource {

    private val runs = db.getCollection<Run>()

    override suspend fun insertRun(run: Run): Boolean {
        return runs.insertOne(run).wasAcknowledged()
    }
}