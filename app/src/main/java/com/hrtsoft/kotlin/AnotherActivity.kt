package com.hrtsoft.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)



        val imageView: ImageView = findViewById(R.id.anotherImageView)
        val textView: TextView = findViewById(R.id.anotherTextView)
        val description: TextView = findViewById(R.id.anotherDescription)

        // Retrieve data from the intent
        val imageResId = intent.getIntExtra("image", R.drawable.ic_launcher_foreground)
        val text = intent.getStringExtra("text")
        val des = intent.getStringExtra("description")

        // Set data to the views
        imageView.setImageResource(imageResId)
        textView.text = text
        description.text = des
    }
}