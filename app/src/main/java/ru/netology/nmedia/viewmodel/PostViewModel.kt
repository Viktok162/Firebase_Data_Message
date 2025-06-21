package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data: LiveData<List<Post>> = repository.get()

    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun look(id: Long) = repository.look(id)
    fun removeById(id: Long) = repository.removeById(id)

}