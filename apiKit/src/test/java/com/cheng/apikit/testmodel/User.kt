package com.cheng.apikit.testmodel

class User(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val status: String,
)

class GetUsersResponse(
    val data: List<User>
)

class CreateUserResponse(
    val data: User
)
