package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу." +
                    "Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и" +
                    " помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное" +
                    " остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше," +
                    " целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста" +
                    " и начать цепочку перемен → http://netolo.gy/fyb\""
        )
        with(binding){
            author.text = post.author
            content.text = post.content
            published.text = post.published
            liked.text = post.likes.toString()
            shared.text = post.shares.toString()

            if (post.likeByMe){
                heart.setImageResource(R.drawable.heart_red_24dp)
            }

            heart.setOnClickListener {
                post.likeByMe = !post.likeByMe

                heart.setImageResource(
                    if (post.likeByMe){
                        R.drawable.heart_red_24dp
                    } else {
                        R.drawable.heart_white_24dp
                    }
                )
                post.likes += if (post.likeByMe) 1 else -1
                liked.text = post.likes.toString()
            }

            share.setOnClickListener {
                post.shares += 1
                shared.text = post.shares.toString()

            }
        }
    }
}