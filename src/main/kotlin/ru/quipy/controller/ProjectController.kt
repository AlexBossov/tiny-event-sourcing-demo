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
    fun createProject(@PathVariable projectTitle: String, @RequestParam userId: UUID) : ProjectCreatedEvent {
        return projectEsService.create { it.create(UUID.randomUUID(), projectTitle, userId) }
    }

    @GetMapping("/{projectId}")
    fun getAccount(@PathVariable projectId: UUID) : ProjectAggregateState? {
        return projectEsService.getState(projectId)
    }

//    @PostMapping("/{projectId}/tasks/{taskName}")
//    fun createTask(@PathVariable projectId: UUID,
//                   @RequestParam taskName: String,
//                   @RequestParam taskDescription: String?) : TaskCreatedEvent {
//        return projectEsService.update(projectId) {
//            it.addTask(taskName, taskDescription)
//        }
//    }
}