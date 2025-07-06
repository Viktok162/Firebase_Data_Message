package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import ru.netology.nmedia.databinding.AcNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AcNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener{
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank()){
                setResult(Activity.RESULT_CANCELED, intent)
            } else{
                val content = binding.edit.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}

