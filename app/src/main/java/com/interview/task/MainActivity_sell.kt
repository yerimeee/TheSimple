package com.interview.task

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
@SuppressLint("StaticFieldLeak")    //Lint를 통해 성능 문제를 관리하는 경고를 무시
class MainActivity_sell : AppCompatActivity() {

    private var t_result : TextView? = null
    private var resultNum : Int = 0
    private var menu1Count : Int = 0
    private var menu2Count : Int = 0
    private var menu3Count : Int = 0
    private var menu4Count : Int = 0
    private var menu5Count : Int = 0
    private var orderNum : Int = 0

    // DB 사용
    lateinit var db : AppDatabase   //나중에 초기화 하기 위한 타입
    var orderList = listOf<Cart>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sell)

        t_result = findViewById(R.id.t_result)
        val btn_order: Button = findViewById(R.id.btn_order)

        // DB 지정
        db = AppDatabase.getInstance(this)!!    //싱글턴 객체 가져옴

        // 현재 날짜, 시간 불러오기
        val localDateTime: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
        val watch = localDateTime.format(formatter)
        val t_watch: TextView = findViewById(R.id.t_watch)
        t_watch.text = watch

        btn_order.setOnClickListener {
            val orders = Cart(null, orderNum, t_menuName, resultNum, menuCount)
            insertOrder(orders)

            // 주문내역 페이지로 이동
            val intent = Intent(this@MainActivity_sell, MainActivity_order::class.java)
            startActivity(intent)
        }
    }

    // DB 연동 해야될 일
    // 1. insert

    fun insertOrder(cart: Cart) {
        CoroutineScope(Dispatchers.IO).launch {//IO스레드에서 코루틴 시작
            db.CartDao().insertCart(cart)   //백그라운드 스레드에서 실행
            withContext(Dispatchers.Main) {//UI스레드에서 처리
                getAllCart()
            }
        }
    }
    // 2. get
    fun getAllCart() {
        CoroutineScope(Dispatchers.IO).launch {
            val orderList = db.CartDao().getAllCart()
            withContext(Dispatchers.Main) {
                setRecyclerView(orderList)
            }
        }
    }

    // 3. delete
    // recycleView 사용(ListView발전)
    fun setRecyclerView() {

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
        orderNum++

        if(t_menuName == "단팥빵") {
            menu1Count++
        } else if (t_menuName == "크림빵") {
            menu2Count++
        } else if (t_menuName == "식빵") {
            menu3Count++
        } else if (t_menuName == "크루아상") {
            menu4Count++
        } else {
            menu5Count++
        }

        // 선택한 메뉴의 이름, 개수, 가격 넘기기

        return resultNum
    }

    // 메뉴 모두 삭제
    fun allDelete(view: View){
        t_result?.text = ""
        resultNum = 0
        menu1Count = 0
        menu2Count = 0
        menu3Count = 0
        menu4Count = 0
        menu5Count = 0
    }
}