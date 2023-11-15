package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.ProjectTasksViewDomain
import java.util.*

interface ProjectRepository  : MongoRepository<ProjectTasksViewDomain.ProjectView, UUID> {

    fun findA(nickname: String): ProjectTasksViewDomain.ProjectView

    fun find(): List<ProjectTasksViewDomain.ProjectView>
}