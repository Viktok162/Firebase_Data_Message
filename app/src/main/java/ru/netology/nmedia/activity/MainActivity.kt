package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                content.text = post.content
                published.text = post.published
                liked.text = quantityWritingRule(post.likes)
                shared.text = quantityWritingRule(post.shares)
                looked.text = quantityWritingRule(post.looks)
                heart.setImageResource(
                    if (post.likeByMe) {
                        R.drawable.heart_red_24dp
                    } else {
                        R.drawable.heart_white_24dp
                    }
                )
            }
        }

        binding.heart.setOnClickListener {
            viewModel.like()
        }

        binding.share.setOnClickListener {
            viewModel.share()
        }

        binding.eye.setOnClickListener {
            viewModel.look()
        }
    }
}
