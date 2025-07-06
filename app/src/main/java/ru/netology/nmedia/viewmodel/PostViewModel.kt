package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post (
    id = 0,
    author = "",
    content = "",
    published = "",
    likeByMe = false
)

class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data: LiveData<List<Post>> = repository.get()
    val edited = MutableLiveData(empty)

    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun look(id: Long) = repository.look(id)
    fun removeById(id: Long) = repository.removeById(id)

    fun changeContent(content: String) {
        val text = content.trim()

        edited.value?.let {
            if (text == it.content) {
                return@let
            }
            edited.value = it.copy(content = text)
        }
    }

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post:Post){

        edited.value = post
    }
}

