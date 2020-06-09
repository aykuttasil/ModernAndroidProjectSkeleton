package com.aykuttasil.domain.entities

data class UserEntity(
  val userId: String? = null,
  val userName: String? = null,
  val userEmail: String? = null,
  val userPass: String? = null,
  val userJob: String? = null
)