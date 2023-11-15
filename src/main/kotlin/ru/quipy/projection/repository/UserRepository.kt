package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.UserViewDomain

interface UserRepository : MongoRepository<UserViewDomain.UserView, String> {

    fun findByNickname(nickname: String): UserViewDomain.UserView
    fun findByTa(nickname: String): UserViewDomain.UserView
}