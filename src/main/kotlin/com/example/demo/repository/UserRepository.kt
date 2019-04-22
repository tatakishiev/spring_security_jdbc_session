package com.example.demo.repository

import com.example.demo.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
  fun findByEmail(email: String): User?
}