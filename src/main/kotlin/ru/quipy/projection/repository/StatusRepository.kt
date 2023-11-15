package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.ProjectTasksViewDomain
import java.util.*

interface StatusRepository : MongoRepository<ProjectTasksViewDomain.StatusView, UUID> {
}