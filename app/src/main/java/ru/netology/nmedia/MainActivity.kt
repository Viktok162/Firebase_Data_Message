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
        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            liked.text = quantityWritingRule(post.likes)
            shared.text = quantityWritingRule(post.shares)
            looked.text = quantityWritingRule(post.looks)

            if (post.likeByMe) {
                heart.setImageResource(R.drawable.heart_red_24dp)
            }

            heart.setOnClickListener {
                post.likeByMe = !post.likeByMe

                heart.setImageResource(
                    if (post.likeByMe) {
                        R.drawable.heart_red_24dp
                    } else {
                        R.drawable.heart_white_24dp
                    }
                )
                post.likes += if (post.likeByMe) 1 else -1
                liked.text = quantityWritingRule(post.likes)
            }

            share.setOnClickListener {
                post.shares += 1
                shared.text = quantityWritingRule(post.shares)
            }

            eye.setOnClickListener {
                post.looks += 1
                looked.text = quantityWritingRule(post.looks)
            }
//          Test start
//            binding.root.setOnClickListener{
//                val test = post.shares
//            }
//
//            avatar.setOnClickListener{
//                val test = post.shares
//            }
//          Test finish
//
        }
    }

    private fun quantityWritingRule(number: Int): String {
        var num: Int = number
        var numHundred: Int
        var numThousand: Int
        var numMill: Int
        var numHundOfThous: Int
        if (num < 1000) {
            return num.toString()
        } else if (num < 10000) {
            numThousand = num / 1000
            numHundred = (num % 1000) / 100
            if (numHundred == 0) {
                return numThousand.toString() + "K"
            } else return numThousand.toString() + "." + numHundred + "K"
        } else if (num < 1000000) {
            numThousand = num / 1000
            return numThousand.toString() + "K"
        } else {
            numMill = num / 1000000
            numHundOfThous = (num % 1000000) / 100000
            if (numHundOfThous == 0) {
                return numMill.toString() + "M"
            } else return numMill.toString() + "." + numHundOfThous + "M"

        }
    }
}