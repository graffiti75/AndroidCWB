package carlos.nicolau.galves.core.data

class GetUserRepositoryImpl(private val dataSource: GetUserDataSource) : GetUserRepository {
    override fun execute(username: String, password: String) =
        dataSource.execute("","")
}