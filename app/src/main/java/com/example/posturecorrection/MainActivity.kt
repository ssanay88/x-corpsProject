package com.example.posturecorrection

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG: String = "로그"

    }

    private lateinit var stateFragment:StateFragment
    private lateinit var chartFragment:ChartFragment
    private lateinit var videoFragment:VideoFragment

    private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    private var now = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private var YEAR = now.format(DateTimeFormatter.ofPattern("yyyy")).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private var MONTH = now.format(DateTimeFormatter.ofPattern("MM")).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private var DAY = now.format(DateTimeFormatter.ofPattern("dd")).toString()

    private  val currentDateTime = Calendar.getInstance().time
    private var min = SimpleDateFormat("mm", Locale.KOREA).format(currentDateTime)
    private var sec = SimpleDateFormat("ss", Locale.KOREA).format(currentDateTime)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavItemSelectedLister)
        stateFragment = StateFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.basic_fragment, stateFragment).commit()


        // 실시간 데이터 가져오기
        val docRef = db.collection("MONTH").document("DAY") // 콜렉션 , 문서 변경
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
                val message = snapshot.data.toString()
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "Current data: null")
                Toast.makeText(this, "없어", Toast.LENGTH_SHORT).show()
            }
        }


    }




    //바텀네비게이션 아이템 클릭 리스너 설정
    private val onBottomNavItemSelectedLister = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.page_1 -> {
                Toast.makeText(this, "${YEAR} + ${MONTH}", Toast.LENGTH_SHORT).show()
                stateFragment = StateFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(
                    R.id.basic_fragment,
                    stateFragment
                ).commit()
                true

            }

            R.id.page_2 -> {
                Toast.makeText(this, "${min} + ${sec} + ${DAY}", Toast.LENGTH_SHORT).show()
                chartFragment = ChartFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(
                    R.id.basic_fragment,
                    chartFragment
                ).commit()

                true
            }

            R.id.page_3 -> {
                Toast.makeText(this, "${YEAR} + ${MONTH} + ${DAY}", Toast.LENGTH_SHORT).show()
                videoFragment = VideoFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(
                    R.id.basic_fragment,
                    videoFragment
                ).commit()

                true
            }

            else -> false
        }
    }

    /* 실시간 상황
    fun readObserveData(){
        db.collection("users")
            .document("1")
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var map = p0.value as Map<String, Any>
                    var number = map["age"].toString()
                    when(number){
                        "3"->{
                            Toast.makeText(applicationContext, " 자세를 교정해주세요!.",Toast.LENGTH_SHORT).show()
                            textView1.text = "어깨쪽 자세가 좋지 않습니다."
                            imageView4.setImageResource(R.drawable.left_1)
                            data3 += 1
                        }
                        "2"->{
                            Toast.makeText(applicationContext, " 자세를 교정해주세요!.",Toast.LENGTH_SHORT).show()
                            textView1.text = "어깨쪽 자세가 좋지 않습니다."
                            imageView4.setImageResource(R.drawable.left_2)
                            data2 += 1
                        }
                        "1" -> {
                            Toast.makeText(applicationContext, " 자세를 교정해주세요!.",Toast.LENGTH_SHORT).show()
                            textView1.text = "등쪽 자세가 좋지 않습니다."
                            imageView4.setImageResource(R.drawable.deung_1)
                            data1 += 1
                        }
                        "0" -> {
                            textView1.text = "자세가 좋습니다."
                            imageView4.setImageResource(R.drawable.normaal_1)
                        }
                        else -> {  }
                    }
                }
            })
    }*/

}