package ru.quipy.logic

import ru.quipy.api.*
import java.util.*

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun ProjectAggregateState.create(id: UUID, title: String, userId: UUID): ProjectCreatedEvent {
    return ProjectCreatedEvent(
        projectId = id,
        title = title,
        userId = userId
    )
}

fun ProjectAggregateState.createTag(name: String): TagCreatedEvent {
    if (projectTags.values.any { it.name == name }) {
        throw IllegalArgumentException("Tag already exists: $name")
    }
    return TagCreatedEvent(projectId = this.getId(), tagId = UUID.randomUUID(), tagName = name)
}

fun ProjectAggregateState.assignTagToTask(tagId: UUID, taskId: UUID): TagAssignedToTaskEvent {
    if (!projectTags.containsKey(tagId)) {
        throw IllegalArgumentException("Tag doesn't exists: $tagId")
    }

    if (!tasks.contains(taskId)) {
        throw IllegalArgumentException("Task doesn't exists: $taskId")
    }

    return TagAssignedToTaskEvent(projectId = this.getId(), tagId = tagId, taskId = taskId)
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

fun ProjectAggregateState.addStatusToProject(statusId: UUID, name: String, color: String): StatusAddedToProjectEvent {
    return StatusAddedToProjectEvent(
        projectId = this.getId(),
        statusId = statusId,
        statusName = name,
        statusColor = color)
}