package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl: PostRepository {
    private var index: Long = 1L
    private var posts = listOf(
        Post(
            id = index++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 6.               Знаний хватит на всех.\n" +
                      "Наша миссия — помочь встать на путь роста и начать цепочку перемен.",
            likeByMe = false
        ),
        Post(
            id = index++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 5.      Привет, это новая Нетология!\n Когда-то Нетология начиналась " +
                    "с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну," +
                    " разработке, аналитике и управлению. Наша миссия — помочь встать на" +
                    " путь роста и начать цепочку перемен → http://netolo.gy/fyb\"",
            likeByMe = false
        ),
        Post(
            id = index++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 4.               Знаний хватит на всех.\n" +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен",
            likeByMe = false
        ),
        Post(
            id = index++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 3.      Привет, это новая Нетология!\n Когда-то Нетология начиналась " +
                    "с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну," +
                    " разработке, аналитике и управлению. Наша миссия — помочь встать на" +
                    " путь роста и начать цепочку перемен → http://netolo.gy/fyb\"",
            likeByMe = false
        ),
        Post(
            id = index++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 2.               Знаний хватит на всех.\n" +
                    "Наша миссия — помочь встать на путь роста и начать цепочку перемен",
            likeByMe = false
        ),
        Post(
            id = index++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Post 1.      Привет, это новая Нетология!\n Когда-то Нетология начиналась " +
                    "с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну," +
                    " разработке, аналитике и управлению. Наша миссия — помочь встать на" +
                    " путь роста и начать цепочку перемен → http://netolo.gy/fyb\"",
            likeByMe = false
        )
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

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = listOf(
            post.copy(
                id = index++,
                author = "Me",
                published = "now"
            )
        ) + posts
        data.value = posts
        return
    }
}