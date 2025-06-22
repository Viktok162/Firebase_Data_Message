package ru.netology.nmedia.activity

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(
            onLikeListener = { post ->
                viewModel.like(post.id)
            },
            onShareListener = { post ->
                viewModel.share(post.id)
            },
            onLookListener = { post ->
                viewModel.look(post.id)
            },
            onRemoveListener = { post ->
                viewModel.removeById(post.id)
            }
        )

        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            val isNew = posts.size != adapter.itemCount
            adapter.submitList(posts){
                if (isNew) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        with(binding) {
            save.setOnClickListener {
                if (content.text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.error_empty_content,
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(content.text.toString())
                viewModel.save()
                content.setText("")
                content.clearFocus()
                AndroidUtils.hideKeyboard(it)
            }
        }
    }

    object AndroidUtils{
        fun hideKeyboard(view: View){
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

