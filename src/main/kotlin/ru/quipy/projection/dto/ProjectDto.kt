package ru.quipy.projection.dto

import java.util.*

data class ProjectDto(
    val projectId: UUID,
    val projectTitle: String,
    val createdAt: Long,
    val updatedAt: Long,
)
