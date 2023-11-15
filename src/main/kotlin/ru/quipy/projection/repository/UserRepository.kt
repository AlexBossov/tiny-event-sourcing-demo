package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.UserViewDomain
import java.util.*

interface UserRepository : MongoRepository<UserViewDomain.UserView, UUID> {

    fun findByNickname(nickname: String): UserViewDomain.UserView
}