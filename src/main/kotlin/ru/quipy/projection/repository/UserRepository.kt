package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.UserViewDomain
import java.util.UUID

interface UserRepository : MongoRepository<UserViewDomain.UserView, UUID> {

    fun findByNickname(nickname: String): UserViewDomain.UserView
    fun findByTa(nickname: String): UserViewDomain.UserView
}