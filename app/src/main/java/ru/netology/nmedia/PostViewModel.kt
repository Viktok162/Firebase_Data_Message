package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post

class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data: LiveData<Post> = repository.get()

    fun like() = repository.like()

}