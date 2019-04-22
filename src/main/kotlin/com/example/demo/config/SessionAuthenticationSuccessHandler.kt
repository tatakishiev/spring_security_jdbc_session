package com.example.demo.config

import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SessionAuthenticationSuccessHandler : AuthenticationSuccessHandler {
  override fun onAuthenticationSuccess(
    p0: HttpServletRequest?,
    p1: HttpServletResponse?,
    p2: org.springframework.security.core.Authentication?
  ) {
    p1?.status = HttpServletResponse.SC_OK
  }
}