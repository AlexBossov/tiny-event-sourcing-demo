package ru.quipy.projection.view

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.quipy.domain.Unique
import java.util.*

class UserViewDomain {
    @Document(collection = "user-view")
    data class UserView(
        @Id override val id: UUID,
        val nickname: String,
        val password: String,
        val createdAt: Long = System.currentTimeMillis(),
        val updatedAt: Long = System.currentTimeMillis()
    ) : Unique<UUID>
}