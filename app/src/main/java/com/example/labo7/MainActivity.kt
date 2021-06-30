package com.example.labo7

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lista de patron autogenerado y patron a revisar
        val pattern: MutableList<Int> = ArrayList()
        val userPattern: MutableList<Int> = ArrayList()

        var userInput = 0     //este contador lleva la cantidad de inputs presionados por el usuario
        var flag_init = false   //esta bandera indica si se ha presionado el boton

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
                userInput = 0
                flag_init = false
                pattern.clear()
                userPattern.clear()
            }
            if (i==3){
                Toast.makeText(this@MainActivity, "Secuencia correcta", Toast.LENGTH_SHORT).show()
                userInput = 0
                flag_init = false
                pattern.clear()
                userPattern.clear()
            }
        }

        //estructura de botones. El boton inicial deshabilita todoso los botones
        //y crea una secuencia random la cual se muestra en pantalla de boton a boton
        buttonInit.setOnClickListener(View.OnClickListener {
            flag_init = true
            hyperDisable()
            for (i in 1..4) {
                val randomNum: Int = Random.nextInt(6)
                pattern.add(randomNum)
                if (randomNum==0){
                    //grayColour(button1)
                    test(button1, i, randomNum, music)
                }
                if (randomNum==1){
                    //grayColour(button2)
                    test(button2, i, randomNum, music)
                }
                if (randomNum==2){
                    //grayColour(button3)
                    test(button3, i, randomNum, music)
                }
                if (randomNum==3){
                    //grayColour(button4)
                    test(button4, i, randomNum, music)
                }
                if (randomNum==4){
                    //grayColour(button5)
                    test(button5, i, randomNum, music)
                }
                if (randomNum==5){
                    //grayColour(button6)
                    test(button6, i, randomNum, music)
                }
                Timer().schedule(7000){
                    hyperEnable()
                }
            }

        })

        //Todos los demas botones funcionan igual. Siempre y cuando no se presione
        //el boton inicial, simplemente se reproduce un sonido y cambia a color gris.
        button1.setOnClickListener(View.OnClickListener {
            button1.setBackgroundColor(Color.GRAY)
            playSound(music, 0)
            if (flag_init==true){
                userPattern.add(0)
                EvalPatterns(userInput)
                userInput+=1
            }
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button1.setBackgroundColor(Color.RED)
            }, 1000) // set time as per your requirement

        })

        button2.setOnClickListener(View.OnClickListener {
            button2.setBackgroundColor(Color.GRAY)
            playSound(music, 1)
            if (flag_init==true){
                userPattern.add(1)
                EvalPatterns(userInput)
                userInput+=1
            }
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button2.setBackgroundColor(Color.parseColor("#FF5722"))
            }, 1000) // set time as per your requirement
        })

        button3.setOnClickListener(View.OnClickListener {
            button3.setBackgroundColor(Color.GRAY)
            playSound(music, 2)
            if (flag_init==true){
                userPattern.add(2)
                EvalPatterns(userInput)
                userInput+=1
            }
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button3.setBackgroundColor(Color.parseColor("#FFEB3B"))
            }, 1000) // set time as per your requirement
        })

        button4.setOnClickListener(View.OnClickListener {
            button4.setBackgroundColor(Color.GRAY)
            playSound(music, 3)
            if (flag_init==true){
                userPattern.add(3)
                EvalPatterns(userInput)
                userInput+=1
            }
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button4.setBackgroundColor(Color.parseColor("#4CAF50"))
            }, 1000) // set time as per your requirement
        })

        button5.setOnClickListener(View.OnClickListener {
            button5.setBackgroundColor(Color.GRAY)
            playSound(music, 4)
            if (flag_init==true){
                userPattern.add(4)
                EvalPatterns(userInput)
                userInput+=1
            }
            Handler().postDelayed(Runnable { // This method will be executed once the timer is over
                button5.setBackgroundColor(Color.parseColor("#FF03DAC5"))
            }, 1000) // set time as per your requirement
        })

        button6.setOnClickListener(View.OnClickListener {
            button6.setBackgroundColor(Color.GRAY)
            playSound(music, 5)
            if (flag_init==true){
                userPattern.add(5)
                EvalPatterns(userInput)
                userInput+=1
            }
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

}