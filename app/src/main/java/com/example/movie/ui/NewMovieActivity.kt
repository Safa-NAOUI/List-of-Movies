package com.example.movie.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R

/**
 * Created by Safa NAOUI on 17/02/2021.
 */
class NewMovieActivity : AppCompatActivity() {

    private lateinit var editMovieView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_movie)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        editMovieView = findViewById(R.id.edit_movie)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editMovieView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editMovieView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.movielistsql.REPLY"
    }
}
