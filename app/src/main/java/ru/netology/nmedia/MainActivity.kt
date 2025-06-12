//package ru.netology.nmedia
//
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import ru.netology.nmedia.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    private val viewModel: PostViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        viewModel.data.observe(this) { post ->
//            with(binding) {
//                author.text = post.author
//                content.text = post.content
//                published.text = post.published
//                liked.text = quantityWritingRule(post.likes)
//                shared.text = quantityWritingRule(post.shares)
//                looked.text = quantityWritingRule(post.looks)
//                heart.setImageResource(
//                    if (post.likeByMe) {
//                        R.drawable.heart_red_24dp
//                    } else {
//                        R.drawable.heart_white_24dp
//                    }
//                )
//            }
//        }
//
//            binding.heart.setOnClickListener {
//                viewModel.like()
//            }
//
//            binding.share.setOnClickListener {
////                post.shares += 1
////                shared.text = quantityWritingRule(post.shares)
//            }
//
//            binding.eye.setOnClickListener {
////                post.looks += 1
////                looked.text = quantityWritingRule(post.looks)
//            }
//        }
//    }
//
//    private fun quantityWritingRule(number: Int): String {
//        var num: Int = number
//        var numHundred: Int
//        var numThousand: Int
//        var numMill: Int
//        var numHundOfThous: Int
//        if (num < 1000) {
//            return num.toString()
//        } else if (num < 10000) {
//            numThousand = num / 1000
//            numHundred = (num % 1000) / 100
//            if (numHundred == 0) {
//                return numThousand.toString() + "K"
//            } else return numThousand.toString() + "." + numHundred + "K"
//        } else if (num < 1000000) {
//            numThousand = num / 1000
//            return numThousand.toString() + "K"
//        } else {
//            numMill = num / 1000000
//            numHundOfThous = (num % 1000000) / 100000
//            if (numHundOfThous == 0) {
//                return numMill.toString() + "M"
//            } else return numMill.toString() + "." + numHundOfThous + "M"
//
//        }
//    }
