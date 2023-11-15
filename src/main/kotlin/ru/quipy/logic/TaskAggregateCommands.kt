package ru.quipy.logic

import ru.quipy.api.*
import java.util.*

fun TaskAggregateState.createTask(
    id: UUID,
    projectId: UUID,
    userId: UUID,
    name: String,
    description: String?,
): TaskCreatedEvent {
    return TaskCreatedEvent(
        taskId = id,
        projectId = projectId,
        userId = userId,
        taskName = name,
        taskDescription = description
    )
}

fun TaskAggregateState.updateTask(name: String, description: String?): TaskUpdatedEvent {
    return TaskUpdatedEvent(
        taskId = this.getId(),
        taskName = name,
        taskDescription = description
    )
}

fun TaskAggregateState.addUserToTask(userId: UUID): UserAddedToTaskEvent {
    return UserAddedToTaskEvent(
        taskId = this.getId(),
        userId = userId
    )
}

fun TaskAggregateState.deleteUserFromTask(userId: UUID): UserDeletedFromTaskEvent {
    return UserDeletedFromTaskEvent(
        taskId = this.getId(),
        userId = userId
    )
}

fun TaskAggregateState.addStatusToTask(statusId: UUID): StatusAddedToTaskEvent {
    if (!availableStatuses.contains(statusId)) {
        throw IllegalArgumentException("Status doesn't exist: $statusId")
    }

    return StatusAddedToTaskEvent(
        taskId = this.getId(),
        statusId = statusId)
}

fun TaskAggregateState.addStatusToProject(statusId: UUID): PossibleStatusAddedToProjectEvent {
    return PossibleStatusAddedToProjectEvent(
        taskId = this.getId(),
        statusId = statusId)
}