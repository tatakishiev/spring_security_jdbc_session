package com.example.demo.controller

import com.example.demo.domain.User
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {


  @GetMapping("/all")
  fun getAll(): List<User> = userService.getAll()

  @PostMapping
  fun create(): User = userService.create()

}