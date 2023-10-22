package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.streams.AggregateSubscriptionsManager
import javax.annotation.PostConstruct

@Service
class TaskEventsSubscriber {

    val logger: Logger = LoggerFactory.getLogger(TaskEventsSubscriber::class.java)

    @Autowired
    lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(TaskAggregate::class, "task-subscriber") {

            `when`(TaskCreatedEvent::class) { event ->
                logger.info("Task created: $event.taskName")
            }

            `when`(TaskUpdatedEvent::class) { event ->
                logger.info("Task updated: $event.taskName")
            }

            `when`(TaskDeletedEvent::class) { event ->
                logger.info("Task deleted: $event.taskId")
            }

            `when`(UserAddedToTaskEvent::class) { event ->
                logger.info("User $event.userId added to task ${event.taskId}")
            }

            `when`(UserDeletedFromTaskEvent::class) { event ->
                logger.info("User $event.userId deleted from task ${event.taskId}")
            }
        }
    }
}