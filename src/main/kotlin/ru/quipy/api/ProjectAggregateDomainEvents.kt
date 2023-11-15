package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val PROJECT_CREATED_EVENT = "PROJECT_CREATED_EVENT"
const val USER_ADDED_TO_PROJECT_EVENT = "USER_ADDED_TO_PROJECT_EVENT"
const val USER_DELETED_FROM_PROJECT_EVENT = "USER_DELETED_FROM_PROJECT_EVENT"
const val STATUS_ADDED_TO_PROJECT_EVENT = "STATUS_ADDED_TO_PROJECT_EVENT"
const val STATUS_DELETED_TO_PROJECT_EVENT = "STATUS_DELETED_TO_PROJECT_EVENT"
const val TASK_ADDED_TO_PROJECT_EVENT = "TASK_ADDED_TO_PROJECT_EVENT"


// API
@DomainEvent(name = PROJECT_CREATED_EVENT)
class ProjectCreatedEvent(
    val projectId: UUID,
    val title: String,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = PROJECT_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = USER_ADDED_TO_PROJECT_EVENT)
class UserAddedToProjectEvent(
    val projectId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = USER_ADDED_TO_PROJECT_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = USER_DELETED_FROM_PROJECT_EVENT)
class UserDeletedFromProjectEvent(
    val projectId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = USER_DELETED_FROM_PROJECT_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = STATUS_ADDED_TO_PROJECT_EVENT)
class StatusAddedToProjectEvent(
    val projectId: UUID,
    val statusId: UUID,
    val statusName: String,
    val statusColor: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_ADDED_TO_PROJECT_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = STATUS_DELETED_TO_PROJECT_EVENT)
class StatusDeletedFromProjectEvent(
    val projectId: UUID,
    val statusId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_DELETED_TO_PROJECT_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_ADDED_TO_PROJECT_EVENT)
class TaskAddedToProjectEvent(
    val projectId: UUID,
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = TASK_ADDED_TO_PROJECT_EVENT,
    createdAt = createdAt
)