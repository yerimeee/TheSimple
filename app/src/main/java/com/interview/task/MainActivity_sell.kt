package com.interview.task

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity_sell : AppCompatActivity() {

    private var t_result : TextView? = null
    private var resultNum : Int = 0
    private var menuCount : Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sell)

        t_result = findViewById(R.id.t_result)

        val btn_order: Button = findViewById(R.id.btn_order)

        // 현재 날짜, 시간 불러오기
        val localDateTime: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
        val watch = localDateTime.format(formatter)
        val t_watch: TextView = findViewById(R.id.t_watch)
        t_watch.text = watch

        // 주문내역 페이지로 이동
        btn_order.setOnClickListener {
            val intent = Intent(this@MainActivity_sell, MainActivity_order::class.java)
            startActivity(intent)
        }
    }

    fun menuClick(view : View): Int{
        //버튼의 text 가져오기
        val t_btn = (view as Button).text.toString()

        //메뉴 가격만 가져오기
        val lines = t_btn.split("\n")
        val lastLine = lines.lastOrNull()?: ""
        t_result?.text = ""
        resultNum += lastLine.toInt()
        t_result?.append(resultNum.toString())

        // 메뉴 이름 가져오기
        val index = t_btn.indexOf("\n\n")
        val menuName = t_btn.substring(0, index)
        var t_menuName: String = menuName

        Toast.makeText(view.context, "선택하신 [ ${t_menuName} ] 메뉴가 추가되었습니다."
                        , Toast.LENGTH_SHORT).show()

        // 선택한 메뉴의 이름, 개수, 가격 넘기기


        return resultNum
    }

    // 메뉴 모두 삭제
    fun allDelete(view: View){
        t_result?.text = ""
        resultNum = 0
        menuCount = 0
    }
}