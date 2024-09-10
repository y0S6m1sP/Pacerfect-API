package example.com.data.run

interface RunDataSource {
    suspend fun insertRun(run: Run): Boolean

    suspend fun getRuns(id: String): List<Run>
}