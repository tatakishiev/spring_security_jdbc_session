package com.example.demo.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name = "users")
class User(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val id: Long?,

  @Column(name = "username")
  val email: String,

  @Column(name = "password")
  val authPassword: String

) : UserDetails {

  override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()

  override fun isEnabled(): Boolean = true

  override fun getUsername(): String = email

  override fun getPassword(): String = authPassword

  override fun isCredentialsNonExpired(): Boolean = true

  override fun isAccountNonExpired(): Boolean = true

  override fun isAccountNonLocked(): Boolean = true
}