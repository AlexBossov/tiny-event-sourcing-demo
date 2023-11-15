package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class ProjectAggregateState : AggregateState<UUID, ProjectAggregate> {
    private lateinit var projectId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var projectTitle: String
    var users = mutableListOf<UUID>()
    var tasks = mutableListOf<UUID>()
    var statuses = mutableMapOf<UUID, StatusEntity>()

    override fun getId() = projectId

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun projectCreatedApply(event: ProjectCreatedEvent) {
        projectId = event.projectId
        projectTitle = event.title
        users.add(event.userId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun taskCreatedApply(event: TaskAddedToProjectEvent) {
        tasks.add(event.taskId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun userAddedToProjectApply(event: UserAddedToProjectEvent) {
        users.add(event.userId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun userDeletedFromProjectApply(event: UserDeletedFromProjectEvent) {
        users.remove(event.userId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun statusAddedToProjectApply(event: StatusAddedToProjectEvent) {
        statuses[event.statusId] = StatusEntity(event.statusId, event.statusName, event.statusColor)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun statusDeletedToProjectApply(event: StatusDeletedFromProjectEvent) {
        statuses.remove(event.statusId)
        updatedAt = event.createdAt
    }
}

data class StatusEntity(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val color: String
)
