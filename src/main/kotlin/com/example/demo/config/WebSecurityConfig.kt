package com.example.demo.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.savedrequest.NullRequestCache

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
  private val provider: AuthenticationProvider,
  private val objectMapper: ObjectMapper,
  private val sessionAuthenticationSuccessHandler: SessionAuthenticationSuccessHandler
) : WebSecurityConfigurerAdapter() {

  @Throws(Exception::class)
  protected fun jsonAuthenticationFilter(): JsonAuthenticationFilter {
    val filter = JsonAuthenticationFilter(objectMapper)
    filter.setAuthenticationSuccessHandler(sessionAuthenticationSuccessHandler)
    filter.setAuthenticationFailureHandler(SimpleUrlAuthenticationFailureHandler())
    filter.setAuthenticationManager(authenticationManager())
    return filter
  }


  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http
      .addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java).cors()
      .and()
      .csrf().disable()
      .exceptionHandling()
      .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
      .and()
      .logout()
      .and()
      .authorizeRequests()
      .antMatchers("/api/users").permitAll()
      .antMatchers("/login", "/api/users").permitAll()
      .and()
      .authorizeRequests().antMatchers("/api/**").authenticated()
      .and()
      .requestCache()
      .requestCache(NullRequestCache())
  }


  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.authenticationProvider(provider)
  }
}