package com.mgtantheta.blueprint.core.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val avatarUrl: String,
)
