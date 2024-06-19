package com.interview.task

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity_sell : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sell)

        // 현재 날짜, 시간 불러오기
        val localDateTime: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
        val watch = localDateTime.format(formatter)
        val t_watch: TextView = findViewById(R.id.t_watch)
        t_watch.text = watch

        // 버튼에 텍스트 중복으로 넣기
        val btn_menu1: Button = findViewById(R.id.btn_menu1)
        val btn_menu2: Button = findViewById(R.id.btn_menu2)
        val btn_menu3: Button = findViewById(R.id.btn_menu3)
        val btn_menu4: Button = findViewById(R.id.btn_menu4)
        val btn_menu5: Button = findViewById(R.id.btn_menu5)

        val t_btn1 = SpannableStringBuilder("제육덮밥\n\n9000")
        val t_btn2 = SpannableStringBuilder("라면\n\n3500")
        val t_btn3 = SpannableStringBuilder("김밥\n\n2900")
        val t_btn4 = SpannableStringBuilder("볶음밥\n\n8000")
        val t_btn5 = SpannableStringBuilder("육개장\n\n9000")

        btn_menu1.text = t_btn1
        btn_menu2.text = t_btn2
        btn_menu3.text = t_btn3
        btn_menu4.text = t_btn4
        btn_menu5.text = t_btn5


    }
}