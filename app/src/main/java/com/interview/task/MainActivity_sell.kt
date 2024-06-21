package com.interview.task

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity_sell : AppCompatActivity() {

    private var t_result : TextView? = null
    private var t_menuNum : TextView? = null
    private var t_menuName : TextView? = null
    private var resultNum : Int = 0
    private var menuCount : Int = 0
    private var scondNUm : Int = 0

    // 버튼들의 ID를 리스트로 정의
    private val btnIds = listOf(R.id.btn_menu1, R.id.btn_menu2, R.id.btn_menu3,
                                    R.id.btn_menu4, R.id.btn_menu5)

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

        t_result = findViewById(R.id.t_result)
        t_menuNum = findViewById(R.id.t_menuNum)
        t_menuName = findViewById(R.id.t_menuName)
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
        t_menuName?.text = ""
        t_menuName?.text = menuName

        // 해당 메뉴 개수 증가
        menuCount ++
        val menuCnt = menuCount.toString()
        t_menuNum?.text = menuCnt


        return resultNum
    }

    // 메뉴 개수 증감
    fun minus(view: View, resultNum: Int){
        //val resultNum = menuClick(view)
        Log.d("MyTag", "resultNum = $resultNum" )

        if (menuCount > 1) {
            menuCount --
            val menuCnt = menuCount.toString()
            t_menuNum?.text = menuCnt

            // 가격 삭감
            val totalNum = t_result?.text?.toString()?.toIntOrNull()
            Log.d("MyTag", "totalNum = $totalNum" )
            if (totalNum != null) {
                val clearNum = totalNum - resultNum
                Log.d("MyTag", "clearNum = $clearNum" )
                t_result?.append(clearNum.toString())
                Log.d("MyTag", "삭감된 가격 = ${t_result?.text}" )
            }else {
                println("유효한 값이 아닙니다.")
            }
        }

    }
    fun plus(view: View){
        if (menuCount > 1) {
            menuCount ++
            val menuCnt = menuCount.toString()
            t_menuNum?.text = menuCnt
        }
    }


    // 메뉴 모두 삭제
    fun allDelete(view: View){
        t_result?.text = ""
        resultNum = 0
        t_menuName?.text = ""
        t_menuNum?.text = ""
    }
}