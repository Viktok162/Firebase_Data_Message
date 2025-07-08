package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractorListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    private val editPostLauncher = registerForActivityResult(EditPostResultContract())
    { newContent ->
        if (newContent == null) {
            viewModel.edited.value = null
            return@registerForActivityResult
        }

        viewModel.changeContent(newContent)
        viewModel.save()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(
            object: OnInteractorListener{
                override fun onLike(post: Post) {
                    viewModel.like(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onEdit(post: Post) {
                    editPostLauncher.launch(post.content)
                    viewModel.edit(post)
                }

                override fun onShare(post: Post) {
                    val intent = Intent().apply{
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT,post.content)
                        type ="text/plain"
                    }
                    val intent2 = Intent.createChooser(intent,getString(R.string.chooser_share_post))
                    startActivity(intent2)
                }

                override fun onEye(post: Post) {
                    viewModel.look(post.id)
                }

                override fun onPlayVideo(post: Post){
                    val videoUri = post.video?.toUri()
                    val intent = Intent(Intent.ACTION_VIEW, videoUri)
                    startActivity(intent)
                }
            }
        )

        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            val isNew = posts.size > adapter.itemCount  // posts.size != adapter.itemCount
            adapter.submitList(posts)
                    {
                if (isNew) {
                    binding.list.smoothScrollToPosition(0)  // scroll up add new post
                }
            }
        }

        val newPostLauncher =  registerForActivityResult(NewPostResultContract()){ content ->
            content ?: return@registerForActivityResult
            viewModel.changeContent(content)
            viewModel.save()
        }

        binding.fab.setOnClickListener {
            newPostLauncher.launch(Unit)
        }
    }
}
