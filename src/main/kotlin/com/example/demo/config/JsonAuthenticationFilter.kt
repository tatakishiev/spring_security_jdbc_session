package com.example.demo.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JsonAuthenticationFilter(private val objectMapper: ObjectMapper) :
  UsernamePasswordAuthenticationFilter() {

  override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
    val loginRequest = objectMapper.readValue(request.reader, LoginRequest::class.java)
    val token = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)

    return this.authenticationManager.authenticate(token)
  }

}