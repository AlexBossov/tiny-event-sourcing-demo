package ru.quipy.projection.controller

import org.springframework.web.bind.annotation.GetMapping
import ru.quipy.projection.service.ProjectTasksService
import java.util.*

class ProjectTasksController(
    val projectTasksService: ProjectTasksService
) {
    @GetMapping()
    fun getAll(): ProjectDto {
        return projectTasksService;
    }
}

data class ProjectDto(
    val projectId: UUID,
    val projectTitle: String,
    val createdAt: Long,
    val updatedAt: Long,
)