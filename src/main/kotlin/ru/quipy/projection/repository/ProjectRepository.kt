package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.ProjectTasksViewDomain
import ru.quipy.projection.view.UserViewDomain
import java.util.UUID

interface ProjectRepository : MongoRepository<ProjectTasksViewDomain.ProjectView, UUID> {

}