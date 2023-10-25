package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.ProjectAggregate
import ru.quipy.api.ProjectCreatedEvent
import ru.quipy.api.UserAddedToProjectEvent
import ru.quipy.api.UserDeletedFromProjectEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.ProjectAggregateState
import ru.quipy.logic.addUserToProject
import ru.quipy.logic.create
import ru.quipy.logic.deleteUserFromProject
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

    @PatchMapping("/addUser/{projectId}")
    fun addUser(@PathVariable projectId: UUID, @RequestParam userId: UUID): UserAddedToProjectEvent {
        return projectEsService.update(projectId) {
            it.addUserToProject(userId)
        }
    }

    @PatchMapping("/deleteUser/{projectId}")
    fun deleteUser(@PathVariable projectId: UUID, @RequestParam userId: UUID): UserDeletedFromProjectEvent {
        return projectEsService.update(projectId) {
            it.deleteUserFromProject(userId)
        }
    }
}