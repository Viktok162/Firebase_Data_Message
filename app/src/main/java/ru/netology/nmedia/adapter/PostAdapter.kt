package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.quantityWritingRule
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnLikeListener = (post: Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnLikeListener
) : RecyclerView.Adapter<PostViewHolder>() {

    var list: List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) = with(binding){
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
        heart.setOnClickListener {
            onLikeListener(post)
        }


    }

}