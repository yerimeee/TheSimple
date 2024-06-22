package com.interview.task

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity_home : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

        // 현재 날짜, 시간 불러오기
        val localDateTime: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
        val watch = localDateTime.format(formatter)

        val t_watch: TextView = findViewById(R.id.t_watch)
        t_watch.text = watch

        //버튼 객체 생성
        val btn_sell: Button = findViewById(R.id.btn_sell)
        val btn_off: Button = findViewById(R.id.btn_off)

        btn_sell.setOnClickListener {
            val intent = Intent(this@MainActivity_home, MainActivity_sell::class.java)
            startActivity(intent)
        }

        btn_off.setOnClickListener { appOff() }
    }
    private fun appOff() {
        // 객체 생성
        val builder = AlertDialog.Builder(this)

        builder.setMessage("정말 종료하시겠습니까?")
            .setPositiveButton("네") { dialog, id ->
                finishAffinity() // 모든 액티비티 종료
            }
            .setNegativeButton("아니요") { dialog, id ->
                dialog.dismiss() //다이얼로그 닫기
            }
        // 다이얼로그 생성 및 보여주기
        val alert = builder.create()
        alert.show()
    }

}
