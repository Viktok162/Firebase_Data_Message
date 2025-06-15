package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likes: Int = 999,
    val shares: Int = 998,
    val looks: Int = 998,
    val likeByMe: Boolean = false
)
