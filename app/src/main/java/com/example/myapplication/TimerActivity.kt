package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.util.*


class TimerActivity : AppCompatActivity() {
    public var START_TIME_IN_MILLIS: Long = 5000
    private lateinit var tvCountTime: TextView
    private lateinit var btnReturn: Button
    private lateinit var btnPause: Button

    private lateinit var mCountDownTimer: CountDownTimer

    private var mTimerRunning: Boolean = false


    private var mTimeLeftInMillis: Long = START_TIME_IN_MILLIS






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer)

        tvCountTime = findViewById(R.id.tvCountTime)

        btnReturn = findViewById(R.id.btnReturn)
        btnPause = findViewById(R.id.btnPause)

        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()

            }

            override fun onFinish() {
                mTimerRunning = false
                //btnPause.visibility = View.INVISIBLE
            }
        }.start()

        mTimerRunning = true
        btnPause.setText("Пауза")

        //слушатель на кнопку сброс
        btnReturn.setOnClickListener {
            btnReturn()
        }


        //слушатель на кнопку пауза
        btnPause.setOnClickListener {
            if (mTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }

        }

        updateCountDownText()



    }




    //вернутся на главное меню (MainActivity)
    public fun btnReturn() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    //остановить работу progressbar
    public fun pauseTimer() {
        mCountDownTimer.cancel()
        mTimerRunning = false
        btnPause.setText("Старт")

    }

    public fun startTimer() {

        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()

            }

            override fun onFinish() {
                mTimerRunning = false
                btnPause.setText("Старт")
                btnPause.visibility = View.INVISIBLE
            }
        }.start()

        mTimerRunning = true
        btnPause.setText("Пауза")


    }

    fun updateCountDownText() {
        var minutes: Int = (mTimeLeftInMillis / 1000 / 60).toInt()
        var seconds: Int = ((mTimeLeftInMillis / 1000) % 60).toInt()

        var timeLeftFormatted: String = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds)

        tvCountTime.setText(timeLeftFormatted)




    }

}