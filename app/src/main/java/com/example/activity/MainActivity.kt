package com.example.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val myRequestCode: Int = 0
    private lateinit var btnNhap : Button
    private lateinit var btnOpen : Button
    private lateinit var txView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNhap=findViewById(R.id.btnNhap)
        btnOpen=findViewById(R.id.btnOpen)
        txView=findViewById(R.id.txView)
        btnNhap.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivityForResult(intent, myRequestCode)
        }

        btnOpen.setOnClickListener{
            val urlDa: String = txView.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlDa))
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (myRequestCode==requestCode && RESULT_OK==resultCode) run {
            val urlReceive: String? = data?.getStringExtra("URL")
            txView.text=urlReceive
        }
    }

    override fun onSaveInstanceState(outState : Bundle){
        super.onSaveInstanceState(outState)
        outState.putString("URL",txView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data= savedInstanceState.getString("URL")
        txView.text=data
    }
}