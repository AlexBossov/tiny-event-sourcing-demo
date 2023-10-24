package ru.quipy.logic

import ru.quipy.api.TaskCreatedEvent
import ru.quipy.api.TaskUpdatedEvent
import ru.quipy.api.UserAddedToTaskEvent
import ru.quipy.api.UserDeletedFromTaskEvent
import java.util.*

fun TaskAggregateState.createTask(id: UUID, projectId: UUID, name: String, description: String?, userId: UUID): TaskCreatedEvent {
    return TaskCreatedEvent(
        taskId = id,
        projectId = projectId,
        userId = userId,
        taskName = name,
        taskDescription = description)
}

fun TaskAggregateState.updateTask(name: String, description: String?): TaskUpdatedEvent {
    return TaskUpdatedEvent(
        taskId = this.getId(),
        taskName = name,
        taskDescription = description)
}

fun TaskAggregateState.addUserToTask(userId: UUID): UserAddedToTaskEvent {
    return UserAddedToTaskEvent(
        taskId = this.getId(),
        userId = userId)
}

fun TaskAggregateState.deleteUserFromTask(userId: UUID): UserDeletedFromTaskEvent {
    return UserDeletedFromTaskEvent(
        taskId = this.getId(),
        userId = userId)
}