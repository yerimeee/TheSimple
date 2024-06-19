package com.interview.task

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //버튼 객체 생성
        val btn_enter: Button = findViewById(R.id.btn_enter)

        btn_enter.setOnClickListener {
            // 두번째 페이지로 이동
            val intent = Intent(this@MainActivity, MainActivity_home::class.java)
            startActivity(intent)
        }


    }
}