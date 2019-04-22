package com.example.demo.config


import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthenticationProviderConfig(
  private val passwordEncoder: PasswordEncoder,
  @Qualifier("userServiceImpl") private val userDetailsService: UserDetailsService
) {

  @Bean
  fun databaseAuthenticationProvider(): AuthenticationProvider {
    val daoAuthenticationProvider = DaoAuthenticationProvider()
    daoAuthenticationProvider.setUserDetailsService(userDetailsService)
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder)
    return daoAuthenticationProvider
  }
}