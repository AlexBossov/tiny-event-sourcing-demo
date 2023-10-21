package ru.quipy.logic

import ru.quipy.api.TaskAggregate
import ru.quipy.api.TaskCreatedEvent
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
    lateinit var userId: UUID

    override fun getId() = taskId

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun taskCreatedApply(event: TaskCreatedEvent) {
        taskId = event.taskId
        taskName = event.taskName
        taskDescription = event.taskDescription
        statusId = event.statusId
        projectId = event.projectId
        userId = event.userId
        createdAt = createdAt
        updatedAt = createdAt
    }
}