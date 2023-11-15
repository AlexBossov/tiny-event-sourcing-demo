package ru.quipy.projection.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.ProjectCreatedEvent
import ru.quipy.api.UserAddedToProjectEvent
import ru.quipy.api.UserAggregate
import ru.quipy.projection.UserEventsSubscriber
import ru.quipy.projection.dto.UserDto
import ru.quipy.projection.repository.ProjectUsersRepository
import ru.quipy.projection.repository.UserRepository
import ru.quipy.projection.view.ProjectUsersViewDomain
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent
import java.util.*

@Service
@AggregateSubscriber(aggregateClass = UserAggregate::class, subscriberName = "project-users-subscriber")
class ProjectUsersService(
    val userRepository: UserRepository,
    val projectUsersRepository: ProjectUsersRepository
) {
    val logger: Logger = LoggerFactory.getLogger(UserEventsSubscriber::class.java)

    @SubscribeEvent
    fun projectCreatedSubscriber(event: ProjectCreatedEvent) {
        logger.info("Project created: {}", event.title)
        projectUsersRepository.save(
            ProjectUsersViewDomain.ProjectUsersView(
                UUID.randomUUID(),
                event.projectId,
                event.userId
            )
        )
    }

    @SubscribeEvent
    fun userAddedToProjectSubscriber(event: UserAddedToProjectEvent) {
        logger.info("User added to project: {}", event.name)
        projectUsersRepository.save(
            ProjectUsersViewDomain.ProjectUsersView(
                UUID.randomUUID(),
                event.projectId,
                event.userId
            )
        )
    }

    fun findUsersByProjectId(projectId: UUID): List<UserDto> {
        val projectUsers = projectUsersRepository.findByProjectId(projectId)
        val userIds = projectUsers.map { it.userId }
        val users = userRepository.findAllById(userIds)
        return users.map { UserDto(it.nickname, it.password) }
    }
}