package ru.quipy.projection.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.ProjectCreatedEvent
import ru.quipy.api.UserAggregate
import ru.quipy.api.UserCreatedEvent
import ru.quipy.projection.UserEventsSubscriber
import ru.quipy.projection.controller.ProjectDto
import ru.quipy.projection.dto.UserDto
import ru.quipy.projection.repository.ProjectRepository
import ru.quipy.projection.repository.UserRepository
import ru.quipy.projection.view.ProjectTasksViewDomain
import ru.quipy.projection.view.UserViewDomain
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Service
@AggregateSubscriber(aggregateClass = UserAggregate::class, subscriberName = "project-tasks-subscriber")
class ProjectTasksService(
    val projectRepository: ProjectRepository
) {
    val logger: Logger = LoggerFactory.getLogger(UserEventsSubscriber::class.java)

    @SubscribeEvent
    fun projectCreatedSubscriber(event: ProjectCreatedEvent) {
        logger.info("Project created: {}", event.title)
        projectRepository.save(
            ProjectTasksViewDomain.ProjectView(
                event.projectId,
                listOf(event.userId),
                event.title,
                event.createdAt
            )
        )
    }

    fun findAll(): List<ProjectDto> {
        val projects = projectRepository.findAll()
        return projects.map { p -> ProjectDto(p.id, p.projectTitle, p.createdAt, p.updatedAt) }
    }
}