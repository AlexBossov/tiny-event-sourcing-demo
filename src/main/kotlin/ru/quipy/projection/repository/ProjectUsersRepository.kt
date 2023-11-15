package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.ProjectUsersViewDomain
import java.util.*

interface ProjectUsersRepository : MongoRepository<ProjectUsersViewDomain.ProjectUsersView, UUID> {

    fun findByProjectId(projectId: UUID): List<ProjectUsersViewDomain.ProjectUsersView>
}