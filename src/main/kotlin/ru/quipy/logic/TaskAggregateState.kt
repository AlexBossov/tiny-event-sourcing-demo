package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

class TaskAggregateState : AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var taskName: String
    var taskDescription: String? = null
    lateinit var statusId: UUID
    lateinit var projectId: UUID
    var users = mutableListOf<UUID>()
    var availableStatuses = mutableListOf<UUID>()

    override fun getId() = taskId

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun taskCreatedApply(event: TaskCreatedEvent) {
        taskId = event.taskId
        taskName = event.taskName
        taskDescription = event.taskDescription
        projectId = event.projectId
        users.add(event.userId)
        createdAt = event.createdAt
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun taskUpdatedApply(event: TaskUpdatedEvent) {
        taskName = event.taskName
        taskDescription = event.taskDescription
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun userAddedToTaskApply(event: UserAddedToTaskEvent) {
        users.add(event.userId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun userDeletedFromTaskApply(event: UserDeletedFromTaskEvent) {
        users.remove(event.userId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun statusAddedToProjectApply(event: PossibleStatusAddedToProjectEvent) {
        availableStatuses.add(event.statusId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun statusAddedToTaskApply(event: StatusAddedToTaskEvent) {
        statusId = event.statusId
        updatedAt = event.createdAt
    }
}