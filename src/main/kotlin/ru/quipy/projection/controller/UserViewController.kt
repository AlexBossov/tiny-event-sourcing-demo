package ru.quipy.projection.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.quipy.projection.dto.UserDto
import ru.quipy.projection.service.UserService

@RestController
@RequestMapping("/user-view")
class UserViewController(
    val userService: UserService
) {

    @GetMapping("/{nickname}")
    fun getUserByNickname(@PathVariable nickname: String): UserDto {
        return userService.findUserByNickName(nickname);
    }
}