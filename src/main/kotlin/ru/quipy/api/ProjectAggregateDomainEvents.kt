package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val PROJECT_CREATED_EVENT = "PROJECT_CREATED_EVENT"
const val USER_ADDED_TO_PROJECT = "USER_ADDED_TO_PROJECT"
const val USER_DELETED_FROM_PROJECT = "USER_DELETED_FROM_PROJECT"
const val STATUS_ADDED_TO_PROJECT = "STATUS_ADDED_TO_PROJECT"
const val STATUS_DELETED_TO_PROJECT = "STATUS_DELETED_TO_PROJECT"
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

@DomainEvent(name = USER_ADDED_TO_PROJECT)
class UserAddedToProjectEvent(
    val projectId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = USER_ADDED_TO_PROJECT,
    createdAt = createdAt
)

@DomainEvent(name = USER_DELETED_FROM_PROJECT)
class UserDeletedFromProjectEvent(
    val projectId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = USER_DELETED_FROM_PROJECT,
    createdAt = createdAt
)

@DomainEvent(name = STATUS_ADDED_TO_PROJECT)
class StatusAddedToProjectEvent(
    val projectId: UUID,
    val statusId: UUID,
    val statusName: String,
    val statusColor: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_ADDED_TO_PROJECT,
    createdAt = createdAt
)

@DomainEvent(name = STATUS_DELETED_TO_PROJECT)
class StatusDeletedFromProjectEvent(
    val projectId: UUID,
    val statusId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_DELETED_TO_PROJECT,
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