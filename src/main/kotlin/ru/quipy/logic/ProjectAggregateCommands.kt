package ru.quipy.logic

import ru.quipy.api.*
import java.util.*

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun ProjectAggregateState.create(id: UUID, title: String, userId: UUID): ProjectCreatedEvent {
    return ProjectCreatedEvent(
        projectId = id,
        title = title,
        userId = userId,
    )
}

fun ProjectAggregateState.addUserToProject(userId: UUID): UserAddedToProjectEvent {
    return UserAddedToProjectEvent(
        projectId = this.getId(),
        userId = userId)
}

fun ProjectAggregateState.deleteUserFromProject(userId: UUID): UserDeletedFromProjectEvent {
    return UserDeletedFromProjectEvent(
        projectId = this.getId(),
        userId = userId)
}

fun ProjectAggregateState.addTaskToProject(taskId: UUID): TaskAddedToProjectEvent {
    return TaskAddedToProjectEvent(
        projectId = this.getId(),
        taskId = taskId)
}