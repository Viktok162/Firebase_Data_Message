package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl: PostRepository {

    private var posts = listOf(
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 2. Знаний хватит на всех" +
                        "Наша миссия — помочь встать на путь роста и начать цепочку перемен",
            likeByMe = false
        ),
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 1. Привет, это новая Нетология! Когда-то Нетология начиналась " +
                    "с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну," +
                    " разработке, аналитике и управлению. Наша миссия — помочь встать на" +
                    " путь роста и начать цепочку перемен → http://netolo.gy/fyb\"",
            likeByMe = false
        ),
    )



    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun like(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                val i = if (post.likeByMe) -1 else 1
                post.copy(likes = post.likes + i, likeByMe = !post.likeByMe)
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(shares = post.shares + 1)
            } else post
        }
        data.value = posts
    }

    override fun look(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(looks = post.looks + 1)
            } else post
        }
        data.value = posts
    }
}