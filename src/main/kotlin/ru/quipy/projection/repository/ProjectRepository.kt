package ru.quipy.projection.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projection.view.ProjectTasksViewDomain
import ru.quipy.projection.view.UserViewDomain

interface ProjectRepository  : MongoRepository<ProjectTasksViewDomain.ProjectView, String> {

    fun findA(nickname: String): ProjectTasksViewDomain.ProjectView

    fun find(): List<ProjectTasksViewDomain.ProjectView>
}