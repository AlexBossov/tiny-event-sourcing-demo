package ru.quipy.projection.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.UserAggregate
import ru.quipy.api.UserCreatedEvent
import ru.quipy.projection.dto.UserDto
import ru.quipy.projection.repository.UserRepository
import ru.quipy.projection.view.UserViewDomain
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Service
@AggregateSubscriber(aggregateClass = UserAggregate::class, subscriberName = "user-subscriber")
class UserService(
    val userRepository: UserRepository
) {

    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    @SubscribeEvent
    fun userCreatedSubscriber(event: UserCreatedEvent) {
        logger.info("User created: {}", event.nickname)
        userRepository.save(UserViewDomain.UserView(
            event.userId,
            event.nickname,
            event.password
        ))
    }

    fun findUserByNickName(nickname: String): UserDto {
        val user = userRepository.findByNickname(nickname)
        return UserDto(user.nickname, user.password)
    }
}