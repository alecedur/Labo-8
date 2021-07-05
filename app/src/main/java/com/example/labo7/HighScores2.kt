package com.example.labo7

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HighScores2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores2)

        val buttonMainMenu = findViewById<Button>(R.id.Menu_Principal)

        val receiver_msg = findViewById<TextView>(R.id.received_value_id)
        val receiver_msg_2 = findViewById<TextView>(R.id.received_value_2_id)
        val receiver_msg_3 = findViewById<TextView>(R.id.received_value_3_id)
        val receiver_msg_4 = findViewById<TextView>(R.id.received_value_4_id)
        val receiver_msg_5 = findViewById<TextView>(R.id.received_value_5_id)

        val received_score_1 = findViewById<TextView>(R.id.received_score_1_id)
        val received_score_2 = findViewById<TextView>(R.id.received_score_2_id)
        val received_score_3 = findViewById<TextView>(R.id.received_score_3_id)
        val received_score_4 = findViewById<TextView>(R.id.received_score_4_id)
        val received_score_5 = findViewById<TextView>(R.id.received_score_5_id)

        // create the get Intent object
        val intent = intent

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        val str = intent.getStringExtra("message_key")

        //display score
        //getting preferences
        val prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)
        //sacamos el score que el usuario acaba de obtener
        val score = prefs.getInt("new_score", 0) //0 is the default value

        //hay que comparar ese valor nuevo con el leaderboard
        val score1 = prefs.getInt("score1", 0)
        val score2 = prefs.getInt("score2", 0)
        val score3 = prefs.getInt("score3", 0)
        val score4 = prefs.getInt("score4", 0)
        val score5 = prefs.getInt("score5", 0)

        if (score > score1) {
            val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            overrideList(4)
            editor.putInt("score1", score)
            editor.putString("score1_player", str)
            editor.commit()
        }
        if (score == score1) {
            val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            overrideList(3)
            editor.putInt("score2", score)
            editor.putString("score2_player", str)
            editor.commit()
        }


        // display the string into textView
        receiver_msg.setText(str)

        received_score_1.setText(score.toString())
        /*received_score_2.setText(score.toString())
        received_score_3.setText(score.toString())
        received_score_4.setText(score.toString())
        received_score_5.setText(score.toString())*/

        buttonMainMenu.setOnClickListener(View.OnClickListener {
            // Start your app main activity

            startActivity(Intent(this,SplashActivity::class.java))

            // close this activity
            finish()
        })

    }

    private fun overrideList(spotstomove: Int) {
        val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val score1 = prefs.getInt("score1", 0)
        val score2 = prefs.getInt("score2", 0)
        val score3 = prefs.getInt("score3", 0)
        val score4 = prefs.getInt("score4", 0)
        val score5 = prefs.getInt("score5", 0)

        val scorename1 = prefs.getString("score1_player", "")
        val scorename2 = prefs.getString("score2_player", "")
        val scorename3 = prefs.getString("score3_player", "")
        val scorename4 = prefs.getString("score4_player", "")
        val scorename5 = prefs.getString("score5_player", "")

        if (spotstomove==1) {
            editor.putInt("score5",score4)
            editor.putString("score5_player",scorename4)
            editor.commit()
        }
        if (spotstomove==2) {
            editor.putInt("score5",score4)
            editor.putString("score5_player",scorename4)
            editor.putInt("score4",score3)
            editor.putString("score4_player",scorename3)
            editor.commit()
        }
        if(spotstomove==3) {
            editor.putInt("score5",score4)
            editor.putString("score5_player",scorename4)
            editor.putInt("score4",score3)
            editor.putString("score4_player",scorename3)
            editor.putInt("score3",score2)
            editor.putString("score3_player",scorename2)
            editor.commit()
        }

        if(spotstomove==4) {
            editor.putInt("score5",score4)
            editor.putString("score5_player",scorename4)
            editor.putInt("score4",score3)
            editor.putString("score4_player",scorename3)
            editor.putInt("score3",score2)
            editor.putString("score3_player",scorename2)
            editor.putInt("score2",score1)
            editor.putString("score2_player",scorename1)
            editor.commit()
        }

    }
}