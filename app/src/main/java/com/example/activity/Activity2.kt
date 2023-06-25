package com.example.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
    private lateinit var btnOk : Button
    private lateinit var edText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        btnOk=findViewById(R.id.btnOK)
        edText=findViewById(R.id.edText)

        btnOk.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("URL",edText.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}