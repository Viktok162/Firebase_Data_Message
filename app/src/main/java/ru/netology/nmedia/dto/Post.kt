package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val published: String,
    val content: String,
    var likes: Int = 999,
    var shares: Int = 998,
    var looks: Int = 998,
    val likeByMe: Boolean = false
)
