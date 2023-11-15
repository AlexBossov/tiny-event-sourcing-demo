package ru.quipy.projection.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.quipy.projection.service.ProjectTasksService
import java.util.*

@RestController
@RequestMapping("/project-tasks-view")
class ProjectTasksViewController(
    private val projectTasksService: ProjectTasksService
) {
    @GetMapping
    fun getAll(): List<ProjectDto> {
        return projectTasksService.findAll()
    }

    @GetMapping("/{projectId}")
    fun getTasksByProjectId(@PathVariable projectId: UUID): List<TaskDto> {
        return projectTasksService.findTasksByProjectId(projectId)
    }

    @GetMapping("/{projectId}")
    fun getProjectBytId(@PathVariable projectId: UUID): ProjectDto? {
        return projectTasksService.findProjectById(projectId)
    }
}

data class ProjectDto(
    val projectId: UUID,
    val projectTitle: String,
    val createdAt: Long,
    val updatedAt: Long,
)

data class TaskDto(
    val projectId: UUID,
    val userId: UUID,
    val name: String,
    val description: String?,
)