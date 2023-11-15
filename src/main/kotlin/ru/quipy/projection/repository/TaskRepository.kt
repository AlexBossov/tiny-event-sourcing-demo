package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.ProjectTasksViewDomain
import java.util.UUID

interface TaskRepository : MongoRepository<ProjectTasksViewDomain.TaskView, UUID> {

    fun findByProjectId(projectId : UUID) : List<ProjectTasksViewDomain.TaskView>
}