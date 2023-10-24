package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/projects")
class ProjectController(
    val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {

    @PostMapping("/{projectTitle}")
    fun createProject(@PathVariable projectTitle: String, @RequestParam userId: UUID): ProjectCreatedEvent {
        return projectEsService.create { it.create(UUID.randomUUID(), projectTitle, userId) }
    }

    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: UUID): ProjectAggregateState? {
        return projectEsService.getState(projectId)
    }

    @PostMapping("/{projectId}")
    fun createTag(@PathVariable projectId: UUID, @RequestParam tagName: String): TagCreatedEvent {
        return projectEsService.update(projectId) { it.createTag(tagName) }
    }

    @PatchMapping("/addUser/{projectId}")
    fun addUser(@PathVariable projectId: UUID, @RequestParam userId: UUID) : UserAddedToProjectEvent {
        return projectEsService.update(projectId) {
            it.addUserToProject(userId)
        }
    }

    @PatchMapping("/deleteUser/{projectId}")
    fun deleteUser(@PathVariable projectId: UUID, @RequestParam userId: UUID) : UserDeletedFromProjectEvent {
        return projectEsService.update(projectId) {
            it.deleteUserFromProject(userId)
        }
    }

    @PatchMapping("/assignTag/{projectId}")
    fun assignTagToTask(@PathVariable projectId: UUID, @RequestParam taskId: UUID, @RequestParam tagId: UUID) : TagAssignedToTaskEvent {
        return projectEsService.update(projectId) {
            it.assignTagToTask(tagId, taskId)
        }
    }
}