package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.AcIntentHandlerBinding

class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AcIntentHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            if(it.action != Intent.ACTION_SEND) return@let

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()){
                Snackbar.make(
                    binding.root,
                    R.string.error_empty_content,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(android.R.string.ok){
                    finish()
                    }.show()
                    return@let
            }

            //     TODO
        }
    }
}


//enableEdgeToEdge()
//setContentView(R.layout.ac_intent_handler)
//ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//    insets
//}