package ru.quipy.logic

import ru.quipy.api.TaskCreatedEvent
import ru.quipy.api.TaskDeletedEvent
import ru.quipy.api.TaskUpdatedEvent
import java.util.*

fun TaskAggregateState.createTask(projectId: UUID, name: String, description: String?, statusId: UUID, userId: UUID): TaskCreatedEvent {
    return TaskCreatedEvent(
        taskId = this.getId(),
        projectId = projectId,
        userId = userId,
        taskName = name,
        taskDescription = description,
        statusId = statusId)
}

fun TaskAggregateState.updateTask(taskId: UUID, name: String, description: String?, statusId: UUID): TaskUpdatedEvent {
    return TaskUpdatedEvent(
        taskId = taskId,
        taskName = name,
        taskDescription = description,
        statusId = statusId)
}

fun TaskAggregateState.deleteTask(taskId: UUID): TaskDeletedEvent {
    return TaskDeletedEvent(taskId = taskId)
}