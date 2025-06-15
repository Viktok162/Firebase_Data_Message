package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.quantityWritingRule
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit
typealias OnLookListener = (post: Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onLookListener: OnLookListener,

) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener, onLookListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onLookListener: OnLookListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) = with(binding){
        author.text = post.author
        content.text = post.content
        published.text = post.published
        liked.text = quantityWritingRule(post.likes)
        shared.text = quantityWritingRule(post.shares)
        looked.text = quantityWritingRule(post.looks)
        heart.setImageResource(
            if (post.likeByMe)R.drawable.heart_red_24dp else R.drawable.heart_white_24dp
        )

        heart.setOnClickListener {
            onLikeListener(post)
        }

        share.setOnClickListener {
            onShareListener(post)
        }

        eye.setOnClickListener {
            onLookListener(post)
        }

    }
}

object PostDiffCallBack: DiffUtil.ItemCallback<Post>(){

    override fun areItemsTheSame(
        oldItem: Post,
        newItem: Post
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Post,
        newItem: Post
    ): Boolean {
        return oldItem == newItem
    }
}