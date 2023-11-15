package ru.quipy.projection.dto

import java.util.*

data class TaskDto(
    val projectId: UUID,
    val userId: UUID?,
    val name: String,
    val description: String?,
)