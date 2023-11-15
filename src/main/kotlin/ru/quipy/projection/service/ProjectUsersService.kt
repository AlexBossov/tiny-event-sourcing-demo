package ru.quipy.projection.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.UserAggregate
import ru.quipy.projection.UserEventsSubscriber
import ru.quipy.projection.dto.UserDto
import ru.quipy.projection.repository.ProjectUsersRepository
import ru.quipy.projection.repository.UserRepository
import ru.quipy.streams.annotation.AggregateSubscriber
import java.util.*

@Service
@AggregateSubscriber(aggregateClass = UserAggregate::class, subscriberName = "project-users-subscriber")
class ProjectUsersService(
    val userRepository: UserRepository,
    val projectUsersRepository: ProjectUsersRepository
) {
    val logger: Logger = LoggerFactory.getLogger(UserEventsSubscriber::class.java)

    fun findUsersByProjectId(projectId: UUID): List<UserDto> {
        val projectUsers = projectUsersRepository.findByProjectId(projectId)
        val userIds = projectUsers.map { it.userId }
        val users = userRepository.findAllById(userIds)
        return users.map { UserDto(it.nickname, it.password) }
    }
}