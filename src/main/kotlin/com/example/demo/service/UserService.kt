package com.example.demo.service

import com.example.demo.domain.User
import com.example.demo.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

interface UserService: UserDetailsService {
  fun getAll(): List<User>
  fun create(): User
}


@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

  @Transactional(readOnly = true)
  override fun loadUserByUsername(p0: String): UserDetails {
    return userRepository.findByEmail("admin") ?: throw EntityNotFoundException()
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


  @Transactional
  override fun create(): User {
    val user = User(
      id = null,
      email = "admin",
      authPassword = passwordEncoder().encode("admin")
    )

    return userRepository.save(user)
  }

  @Transactional(readOnly = true)
  override fun getAll(): List<User> {
    return userRepository.findAll()
  }
}