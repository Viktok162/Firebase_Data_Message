package ru.netology.nmedia.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractorListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    private val editPostLauncher = registerForActivityResult(EditPostResultContract()) { newContent ->
        newContent ?: return@registerForActivityResult
        viewModel.changeContent(newContent)
        viewModel.save()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.editingGroup.visibility = View.GONE  // group

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
//                    viewModel.share(post.id)
                    val intent = Intent().apply{
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT,post.content)
                        type ="text/plain"
                    }
                    val intent2 = Intent.createChooser(intent,getString(R.string.chooser_share_post))
                    startActivity(intent2)

//                    val title = getString(R.string.chooser_share_post)
//                    Toast.makeText(this@MainActivity, "Title: $title", Toast.LENGTH_SHORT).show()
//                    val intent2 = Intent.createChooser(intent, title)
//                    startActivity(intent2)
                }

                override fun onEye(post: Post) {
                    viewModel.look(post.id)
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


//        viewModel.edited.observe(this) { post ->
//            if (post.id != 0L) {
//                oldContent = post.content           //   point 2
//                with(binding.content) {
//                    requestFocus()
//                    // focusAndShowKeyboard()
//                    setText(post.content)
//                }
//                binding.editingGroup.visibility = View.VISIBLE  // group
//            } else{
//                binding.editingGroup.visibility = View.GONE     // group
//                binding.content.setText("")
//                binding.content.clearFocus()
//            }
//        }
//
//        with(binding) {
//            save.setOnClickListener {
//                if (content.text.isNullOrBlank()) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        R.string.error_empty_content,
//                        Toast.LENGTH_LONG
//                    ).show()
//                    return@setOnClickListener
//                }
//                viewModel.changeContent(content.text.toString())
//                viewModel.save()
//                content.setText("")
//                content.clearFocus()
//                AndroidUtils.hideKeyboard(it)
//                editingGroup.visibility = View.GONE     //  group
//            }
//
//            buttonCancel.setOnClickListener {
////                content.setText("")
//                content.setText(oldContent)             //  point 3
//                content.clearFocus()
//                AndroidUtils.hideKeyboard(it)
//                editingGroup.visibility = View.GONE     //  group
//                viewModel.edit(Post(id=0))
//            }
//        }
//    }
//
//    object AndroidUtils{
//        fun hideKeyboard(view: View){
//            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(view.windowToken, 0)
//        }
