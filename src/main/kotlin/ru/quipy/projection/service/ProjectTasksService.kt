package ru.quipy.projection.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.ProjectAggregate
import ru.quipy.api.ProjectCreatedEvent
import ru.quipy.api.TaskCreatedEvent
import ru.quipy.api.UserDeletedFromTaskEvent
import ru.quipy.projection.dto.ProjectDto
import ru.quipy.projection.dto.TaskDto
import ru.quipy.projection.repository.ProjectRepository
import ru.quipy.projection.repository.TaskRepository
import ru.quipy.projection.view.ProjectTasksViewDomain
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent
import java.util.*

@Service
@AggregateSubscriber(aggregateClass = ProjectAggregate::class, subscriberName = "project-tasks-subscriber")
class ProjectTasksService(
    val projectRepository: ProjectRepository,
    val taskRepository: TaskRepository
) {
    val logger: Logger = LoggerFactory.getLogger(ProjectTasksService::class.java)

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

    @SubscribeEvent
    fun taskCreatedSubscriber(event: TaskCreatedEvent) {
        logger.info("Task created: $event.taskId")
        taskRepository.save(
            ProjectTasksViewDomain.TaskView(
                event.taskId,
                event.projectId,
                event.userId,
                event.taskName,
                event.taskDescription,
                event.createdAt
            )
        )
    }

    @SubscribeEvent
    fun userDeletedFromTaskSubscriber(event: UserDeletedFromTaskEvent) {
        logger.info("User $event.userId deleted from task $event.taskId")
        val task = taskRepository.findById(event.taskId).orElse(null) ?: return
        task.userId = null
    }

    fun findAll(): List<ProjectDto> {
        val projects = projectRepository.findAll()
        return projects.map { p -> ProjectDto(p.id, p.projectTitle, p.createdAt, p.updatedAt) }
    }

    fun findTasksByProjectId(projectId: UUID): List<TaskDto> {
        val tasks = taskRepository.findByProjectId(projectId)
        return tasks.map { t -> TaskDto(t.projectId, t.userId, t.name, t.description) }
    }

    fun findProjectById(projectId: UUID): ProjectDto? {
        val project = projectRepository.findById(projectId).orElse(null) ?: return null
        return ProjectDto(project.id, project.projectTitle, project.createdAt, project.updatedAt)
    }
}