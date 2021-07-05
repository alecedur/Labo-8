package com.example.labo7

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lista de patron autogenerado y patron a revisar
        val pattern: MutableList<Int> = ArrayList()
        val userPattern: MutableList<Int> = ArrayList()

        var userInput = 0     //este contador lleva la cantidad de inputs presionados por el usuario
        var flag_init = false   //esta bandera indica si se ha presionado el boton
        var max_sequence = 1
        var score = 0

        val text_high_score = findViewById<TextView>(R.id.highscore_id)

        //inicializamos los botones
        val buttonInit = findViewById<Button>(R.id.Inicio)
        val button1 = findViewById<Button>(R.id.boton1)
        val button2 = findViewById<Button>(R.id.boton2)
        val button3 = findViewById<Button>(R.id.boton3)
        val button4 = findViewById<Button>(R.id.boton4)
        val button5 = findViewById<Button>(R.id.boton5)
        val button6 = findViewById<Button>(R.id.boton6)

        //inicializamos la musica
        val music: MutableList<Int> = ArrayList()
        music.add(R.raw.sound_do)
        music.add(R.raw.sound_re)
        music.add(R.raw.sound_mi)
        music.add(R.raw.sound_fa)
        music.add(R.raw.sound_sol)
        music.add(R.raw.sound_la)
        music.add(R.raw.sound_si)

        //habilita todos los botones
        fun hyperEnable(){
            buttonInit.isClickable = true
            button1.isClickable = true
            button2.isClickable = true
            button3.isClickable = true
            button4.isClickable = true
            button5.isClickable = true
            button6.isClickable = true
        }

        fun update_on_screen_score(){
            score = max_sequence-1
            text_high_score.setText("Highscore: " + score.toString())
        }

        //deshabilita todos los botones
        fun hyperDisable(){
            buttonInit.isClickable = false
            button1.isClickable = false
            button2.isClickable = false
            button3.isClickable = false
            button4.isClickable = false
            button5.isClickable = false
            button6.isClickable = false
        }

        //funcion recibe la cantidad de numeros ingresados por el usuario, evalua ambas listas
        //si son iguales no hace nada, si son diferentes ya se sabe que el usuario ingreso algo mal
        //si el contador es 3 entonces ya se ingresaron los 4 botones, y si no se cumple el primer
        fun EvalPatterns(i: Int){
            if (pattern[i]!=userPattern[i]){
                Toast.makeText(this@MainActivity, "Secuencia incorrecta", Toast.LENGTH_SHORT).show()
                update_highscores(max_sequence)
                userInput = 0   //reset al counter de valores metidos por el usuario
                max_sequence = 1    //reset al counter de secuencias
                flag_init = false
                pattern.clear()     //se reinicia el patron generado
                userPattern.clear() //se reinicia el patron ingresado por el usuario
                update_on_screen_score()
                //game finished, go to next screen.
                startActivity(Intent(this,SendHighScore::class.java))
                // close this activity
                finish()
            }
            if (i==max_sequence-1){
                Toast.makeText(this@MainActivity, "Secuencia correcta", Toast.LENGTH_SHORT).show()
                userInput = 0
                flag_init = false
                //pattern.clear()
                max_sequence+=1
                update_on_screen_score()
                userPattern.clear()
                buttonInit.performClick()
            }
            else {
                userInput += 1
            }
        }

        //estructura de botones. El boton inicial deshabilita todoso los botones
        //y crea una secuencia random la cual se muestra en pantalla de boton a boton
        buttonInit.setOnClickListener(View.OnClickListener {
            //while (!should_restart){
                flag_init = true
                hyperDisable()
                for (i in 1..max_sequence) {
                    if(i==1){
                        val randomNum: Int = Random.nextInt(6)
                        pattern.add(randomNum)
                    }
                    if (pattern[i-1]==0){
                        //grayColour(button1)
                        test(button1, i, pattern[i-1], music)
                    }
                    if (pattern[i-1]==1){
                        //grayColour(button2)
                        test(button2, i, pattern[i-1], music)
                    }
                    if (pattern[i-1]==2){
                        //grayColour(button3)
                        test(button3, i, pattern[i-1], music)
                    }
                    if (pattern[i-1]==3){
                        //grayColour(button4)
                        test(button4, i, pattern[i-1], music)
                    }
                    if (pattern[i-1]==4){
                        //grayColour(button5)
                        test(button5, i, pattern[i-1], music)
                    }
                    if (pattern[i-1]==5){
                        //grayColour(button6)
                        test(button6, i, pattern[i-1], music)
                    }
                    Timer().schedule((1000*max_sequence).toLong()){
                        hyperEnable()
                    }
                //}
            }

        })

        //Todos los demas botones funcionan igual. Siempre y cuando no se presione
        //el boton inicial, simplemente se reproduce un sonido y cambia a color gris.
        button1.setOnClickListener(View.OnClickListener {
            if (flag_init==true){
                userPattern.add(0)
                EvalPatterns(userInput)
                //userInput+=1
            }
            button1.setBackgroundColor(Color.GRAY)
            playSound(music, 0)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button1.setBackgroundColor(Color.RED)
            }, 1000) // set time as per your requirement
        })

        button2.setOnClickListener(View.OnClickListener {
            if (flag_init==true){
                userPattern.add(1)
                EvalPatterns(userInput)
                //userInput+=1
            }
            button2.setBackgroundColor(Color.GRAY)
            playSound(music, 1)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button2.setBackgroundColor(Color.parseColor("#FF5722"))
            }, 1000) // set time as per your requirement
        })

        button3.setOnClickListener(View.OnClickListener {
            if (flag_init==true){
                userPattern.add(2)
                EvalPatterns(userInput)
                //userInput+=1
            }
            button3.setBackgroundColor(Color.GRAY)
            playSound(music, 2)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button3.setBackgroundColor(Color.parseColor("#FFEB3B"))
            }, 1000) // set time as per your requirement
        })

        button4.setOnClickListener(View.OnClickListener {
            if (flag_init==true){
                userPattern.add(3)
                EvalPatterns(userInput)
                //userInput+=1
            }
            button4.setBackgroundColor(Color.GRAY)
            playSound(music, 3)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button4.setBackgroundColor(Color.parseColor("#4CAF50"))
            }, 1000) // set time as per your requirement
        })

        button5.setOnClickListener(View.OnClickListener {
            if (flag_init==true){
                userPattern.add(4)
                EvalPatterns(userInput)
                //userInput+=1
            }
            button5.setBackgroundColor(Color.GRAY)
            playSound(music, 4)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button5.setBackgroundColor(Color.parseColor("#FF03DAC5"))
            }, 1000) // set time as per your requirement
        })

        button6.setOnClickListener(View.OnClickListener {

            if (flag_init==true){
                userPattern.add(5)
                EvalPatterns(userInput)
                //userInput+=1
            }
            button6.setBackgroundColor(Color.GRAY)
            playSound(music, 5)
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button6.setBackgroundColor(Color.parseColor("#2196F3"))
            }, 1000) // set time as per your requirement
        })
    }
    private fun playSound(music: MutableList<Int>, button: Int) {
        val sound = music[button]
        val song = MediaPlayer.create(this, sound)
        song.start()
    }

    //esta funcion carga un timer a partir del valor de la iteracion actual, de manera que se
    //crea un delay artificial entre botones. Entonces por iteracion del for en buttonInit se
    //cambia de color el primer boton y se agrega un timeout para el siguiente boton
    private fun test(button: Button, counter: Int, pattern:Int, music: MutableList<Int>){
        val timeout : Int = 1000*counter
        var tTimeout = timeout.toLong()
        if(counter == 1) {
            button.setBackgroundColor(Color.GRAY)
            playSound(music, pattern)
        }
        else {
            Timer().schedule(tTimeout) {
                playSound(music, pattern)
                button.setBackgroundColor(Color.GRAY)
            }
        }
        if(counter !=1){
            tTimeout += 1000
        }

        Timer().schedule(tTimeout){
            if (pattern==0){
                //playSound(music, pattern)
                button.setBackgroundColor(Color.RED)
            }
            if (pattern==1){
                //playSound(music, pattern)
                button.setBackgroundColor(Color.parseColor("#FF5722"))
            }
            if (pattern==2){
                //playSound(music, pattern)
                button.setBackgroundColor(Color.parseColor("#FFEB3B"))
            }
            if (pattern==3){
                //playSound(music, pattern)
                button.setBackgroundColor(Color.parseColor("#4CAF50"))
            }
            if (pattern==4){
                //playSound(music, pattern)
                button.setBackgroundColor(Color.parseColor("#FF03DAC5"))
            }
            if (pattern==5){
                //playSound(music, pattern)
                button.setBackgroundColor(Color.parseColor("#2196F3"))
            }
        }
    }

    private fun update_highscores(new_score: Int){
        //setting preferences
        val prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt("new_score", new_score)
        editor.putInt("score_number", 0)
        editor.commit()
    }

}