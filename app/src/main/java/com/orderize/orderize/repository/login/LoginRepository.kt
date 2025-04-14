package com.orderize.orderize.repository.login

import com.orderize.orderize.model.NetResource
import com.orderize.orderize.model.User
import com.orderize.orderize.repository.AppDataStore
import com.orderize.orderize.repository.CommonRepository
import com.orderize.orderize.repository.entity.UserEntity
import com.orderize.orderize.repository.login.local.UserDao
import com.orderize.orderize.repository.login.network.LoginService
import com.orderize.orderize.repository.login.network.dto.LoginRequestDto
import com.orderize.orderize.repository.login.network.dto.UserResponseDto

class LoginRepository(
    private val service: LoginService,
    private val dataStore: AppDataStore,
    private val userDao: UserDao
) : CommonRepository(dataStore) {

    suspend fun login(
        email: String,
        password: String
    ): NetResource<User> {
        val tokenResponse = service.authenticateUser(
            LoginRequestDto(
                email,
                password
            )
        )

        if (tokenResponse !is NetResource.Success) {
            throw Exception("Login inváido, email ou senha incorretos!")
        }

        val userResponse = service.getUserByEmail(
            email,
            tokenResponse.data.token
        )

        if (userResponse is NetResource.Success) {
            val user = userResponse.data.toUser()
            if (user.role.isBlank()) {
                return NetResource.Fail("Usuário não possui Cargo válido")
            }
            dataStore.saveToken(tokenResponse.data.token)
            userDao.saveUser(user.toEntity(tokenResponse.data.token))
            return NetResource.Success(user)
        } else {
            return NetResource.Fail("Login inváido, email ou senha incorretos!")
        }
    }

    suspend fun getSavedUser(token: String) = userDao.getUserByApiToken(token)

}

fun UserResponseDto.toUser(): User {
    var role: String = " "
    if (roles.firstOrNull { it.name == "PIZZA_MAKER" } != null) {
        role = roles.first { it.name == "PIZZA_MAKER" }.name
    } else if (roles.firstOrNull { it.name == "SALOON" || it.name == "OWNER" } != null) {
        role = "SALOON"
    }
    return User(
        id = id,
        name = name,
        email = email,
        companyId = companyId.id,
        role = role
    )
}

fun User.toEntity(token: String) =
    UserEntity(
        id = id,
        name = name,
        email = email,
        companyId = companyId,
        role = role,
        apiToken = token
    )