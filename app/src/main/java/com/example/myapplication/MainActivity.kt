package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var tvPreparationTime: TextView
    private lateinit var tvbBreathTime: TextView
    private lateinit var tvHoldingTime: TextView
    private lateinit var tvSetsTime: TextView
    private lateinit var btnStart: Button
    private lateinit var sPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.start);
        var time: TextView = findViewById(R.id.tvTime);

        tvPreparationTime = findViewById(R.id.tvPreparationTime);
        tvbBreathTime = findViewById(R.id.tvbBreathTime);
        tvHoldingTime = findViewById(R.id.tvHoldingTime);
        tvSetsTime = findViewById(R.id.tvSetsTime);


        //sPref = getPreferences(Context.MODE_PRIVATE)
        sPref = getSharedPreferences("mysettings", Context.MODE_PRIVATE)
        editor = sPref.edit()


        tvPreparationTime.setText(sPref.getString("PREPARATION", tvPreparationTime.text.toString()))
        tvbBreathTime.setText(sPref.getString("BREATH", tvbBreathTime.text.toString()))
        tvHoldingTime.setText(sPref.getString("HOLDING", tvHoldingTime.text.toString()))
        tvSetsTime.setText(sPref.getString("SETS", tvSetsTime.text.toString()))

      //  pref.getString("PREPARATION", "")?.let { Log.d("myLog", it) }

        btnStart.setOnClickListener {
            val intent = Intent(this@MainActivity, TimerActivity::class.java)
            startActivity(intent)
        }

    }








    fun tvClick (view: View) {
        var title: String = ""
        var timeStr: String = ""
        when (view.getId()){
            R.id.tvPreparationTime -> {
                title = getString(R.string.preparation_title)
                timeStr = tvPreparationTime.text.toString()
            }


            R.id.tvbBreathTime -> {
                title = getString(R.string.breath_title)
                timeStr = tvbBreathTime.text.toString()            }

            R.id.tvHoldingTime -> {
                title = tvHoldingTime.text.toString()
                timeStr = tvHoldingTime.text.toString()            }

            R.id.tvSetsTime -> {
                title = tvSetsTime.text.toString()
                timeStr = tvSetsTime.text.toString()            }

        }

        buttonOpenDialogClicked(title, timeStr)


        }











    private fun buttonOpenDialogClicked(title: String, timeStr: String) {

        val dialog : Dialog = Dialog (this);
        dialog.setContentView(R.layout.layout_custom_dialog)

        val tvTitle: TextView = dialog.findViewById(R.id.tvTitle)
        val edTimeM: EditText = dialog.findViewById(R.id.edTimeM)
        val edTimeS: EditText = dialog.findViewById(R.id.edTimeS)
        val btnOk: Button = dialog.findViewById(R.id.btnOk)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)

        //сделать аругоментом tvPreparationTime.text.toString()

        val arr = timeStr.split(":").toTypedArray()
        edTimeM.setText(arr[0])
        edTimeS.setText(arr[1])

        tvTitle.setText(title)

        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val time: String = edTimeM.text.toString() + ":" + edTimeS.text.toString()
                when (title) {
                    getString(R.string.preparation_title) -> {
                        tvPreparationTime.setText(time)
                        editor.putString("PREPARATION", tvPreparationTime.text.toString())
                    }
                    getString(R.string.breath_title) -> {
                        tvbBreathTime.setText(time)
                        editor.putString("BREATH", tvbBreathTime.text.toString())
                    }

                    getString(R.string.holding_title) -> {
                        tvHoldingTime.setText(time)
                        editor.putString("HOLDING", tvHoldingTime.text.toString())
                    }

                    getString(R.string.sets_title) -> {
                        tvSetsTime.setText(time)
                        editor.putString("SETS", tvSetsTime.text.toString())
                    }
                }
                editor.apply();

                dialog.dismiss()


            }
        })


        btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()

            }
        })


        dialog.show()


    }



}