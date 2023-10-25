package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {

    @PostMapping
    fun createTask(@RequestBody task: TaskDto): TaskCreatedEvent {
        return taskEsService.create {
            it.createTask(UUID.randomUUID(), task.projectId, task.userId, task.name, task.description)
        }
    }

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: UUID): TaskAggregateState? {
        return taskEsService.getState(taskId)
    }

    @PatchMapping("/{taskId}")
    fun updateTask(
        @PathVariable taskId: UUID,
        @RequestParam name: String,
        @RequestParam description: String?
    ): TaskUpdatedEvent {
        return taskEsService.update(taskId) {
            it.updateTask(name, description)
        }
    }

    @PatchMapping("/addUser/{taskId}")
    fun addUser(@PathVariable taskId: UUID, @RequestParam userId: UUID): UserAddedToTaskEvent {
        return taskEsService.update(taskId) {
            it.addUserToTask(userId)
        }
    }

    @PatchMapping("/deleteUser/{taskId}")
    fun deleteUser(@PathVariable taskId: UUID, @RequestParam userId: UUID): UserDeletedFromTaskEvent {
        return taskEsService.update(taskId) {
            it.deleteUserFromTask(userId)
        }
    }
}

data class TaskDto(
    val projectId: UUID,
    val userId: UUID,
    val name: String,
    val description: String?,
)