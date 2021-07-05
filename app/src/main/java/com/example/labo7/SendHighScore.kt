package com.example.labo7

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SendHighScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_high_score)
        val sendButton = findViewById<Button>(R.id.send_button_id)
        val sendText = findViewById<EditText>(R.id.send_text_id)

        sendButton.setOnClickListener(View.OnClickListener {
            // Start your app main activity

            val str: String = sendText.getText().toString()

            // Create the Intent object of this class Context() to Second_activity class
            // Create the Intent object of this class Context() to Second_activity class
            val intent = Intent(applicationContext, HighScores2::class.java)

            // now by putExtra method put the value in key, value pair
            // key is message_key by this key we will receive the value, and put the string


            // now by putExtra method put the value in key, value pair
            // key is message_key by this key we will receive the value, and put the string
            intent.putExtra("message_key", str)

            // start the Intent

            // start the Intent
            startActivity(intent)
            //startActivity(Intent(this,HighScores::class.java))

            // close this activity
            finish()
        })

    }
}