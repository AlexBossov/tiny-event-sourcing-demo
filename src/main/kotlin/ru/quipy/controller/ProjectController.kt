package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.ProjectAggregate
import ru.quipy.api.ProjectCreatedEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.ProjectAggregateState
import ru.quipy.logic.create
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
}