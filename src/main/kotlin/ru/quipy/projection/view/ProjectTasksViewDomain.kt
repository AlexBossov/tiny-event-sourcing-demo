package ru.quipy.projection.view

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.quipy.domain.Unique
import java.util.*

class ProjectTasksViewDomain {
    @Document(collection = "user-view")
    data class ProjectView(
        @Id override val id: UUID,
        val users: List<UUID>,
        val projectTitle: String,
        val createdAt: Long = System.currentTimeMillis(),
        val updatedAt: Long = System.currentTimeMillis()
    ) : Unique<UUID>

    @Document(collection = "user-view")
    data class TaskView(
        @Id override val id: UUID,
        val nickname: String,
        val password: String,
        val createdAt: Long = System.currentTimeMillis(),
        val updatedAt: Long = System.currentTimeMillis()
    ) : Unique<UUID>

    @Document(collection = "status-view")
    data class StatusView(
        @Id override val id: UUID,
        val name: String,
        val projectId: UUID,
        val color: String,
        val createdAt: Long = System.currentTimeMillis(),
        val updatedAt: Long = System.currentTimeMillis()
    ) : Unique<UUID>
}