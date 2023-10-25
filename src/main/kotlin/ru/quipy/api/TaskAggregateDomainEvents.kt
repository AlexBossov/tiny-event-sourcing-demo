package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val TASK_CREATED_EVENT = "TASK_CREATED_EVENT"
const val TASK_UPDATED_EVENT = "TASK_UPDATED_EVENT"
const val USER_ADDED_TO_TASK_EVENT = "USER_ADDED_TO_TASK_EVENT"
const val STATUS_ADDED_TO_TASK_EVENT = "STATUS_ADDED_TO_TASK_EVENT"
const val USER_DELETED_FROM_TASK_EVENT = "USER_DELETED_FROM_TASK_EVENT"
const val POSSIBLE_STATUS_ADDED_TO_PROJECT_EVENT = "POSSIBLE_STATUS_ADDED_TO_PROJECT_EVENT"

@DomainEvent(name = TASK_CREATED_EVENT)
class TaskCreatedEvent(
    val projectId: UUID,
    val taskId: UUID,
    val userId: UUID,
    val taskName: String,
    val taskDescription: String?,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASK_CREATED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_UPDATED_EVENT)
class TaskUpdatedEvent(
    val taskId: UUID,
    val taskName: String,
    val taskDescription: String?,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASK_UPDATED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = USER_ADDED_TO_TASK_EVENT)
class UserAddedToTaskEvent(
    val taskId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = USER_ADDED_TO_TASK_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = USER_DELETED_FROM_TASK_EVENT)
class UserDeletedFromTaskEvent(
    val taskId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = USER_DELETED_FROM_TASK_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = STATUS_ADDED_TO_TASK_EVENT)
class StatusAddedToTaskEvent(
    val taskId: UUID,
    val statusId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = STATUS_ADDED_TO_TASK_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = POSSIBLE_STATUS_ADDED_TO_PROJECT_EVENT)
class PossibleStatusAddedToProjectEvent(
    val taskId: UUID,
    val statusId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = POSSIBLE_STATUS_ADDED_TO_PROJECT_EVENT,
    createdAt = createdAt
)

