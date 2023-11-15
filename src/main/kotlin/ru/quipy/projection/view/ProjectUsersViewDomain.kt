package ru.quipy.projection.view

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.quipy.domain.Unique
import java.util.*

class ProjectUsersViewDomain {
    @Document(collection = "user-view")
    data class ProjectUsersView(
        @Id override val id: UUID,
        val userId: UUID,
        val projectId: UUID
    ) : Unique<UUID>
}