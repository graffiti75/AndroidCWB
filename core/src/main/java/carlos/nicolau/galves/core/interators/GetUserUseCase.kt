package carlos.nicolau.galves.core.interators

import carlos.nicolau.galves.core.domain.User
import carlos.nicolau.galves.core.interators.IGetUserUseCase

class GetUserUseCase(): IGetUserUseCase {
    override fun execute(username: String, password: String) = User(true)
}