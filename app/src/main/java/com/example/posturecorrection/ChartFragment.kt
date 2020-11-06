package com.example.posturecorrection

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MotionEventCompat.getY
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import com.google.android.youtube.player.internal.c
import com.google.android.youtube.player.internal.i
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_chart.*
import java.security.KeyStore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val db = FirebaseFirestore.getInstance()

    private var param1: String? = null
    private var param2: String? = null

    private var nowState = 1

    // 시작 년 월 일
    private var start_year: String?= null
    private var start_month: String?= null
    private var start_day: String?= null

    // 끝 년 월 일
    private var end_year: String?= null
    private var end_month: String?= null
    private var end_day: String?= null

    var postion1:Int = 0
    var postion2:Int = 0
    var postion3:Int = 0
    var postion4:Int = 0
    var postion5:Int = 0
    var postion6:Int = 0
    var postion7:Int = 0
    var postion8:Int = 0

    private var calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)

    }

    // 시작일 정하고 현재일 정하고 조회
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Video_button.setOnClickListener {


            when(nowState){
                1 -> {
                    // 정상 자세 스트레칭 영상
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=teikykTaW6I"))
                    view.context.startActivity(intent)
                }
                2 -> {
                    // 등 영상
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=3zc1mGfA5kc&list=WL&index=1&t=4s"))
                    view.context.startActivity(intent)
                }
                3 -> {
                    // 허리 영상
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=2YFY91RB05o&list=WL&index=2&t=29s"))
                    view.context.startActivity(intent)
                }
                4 -> {
                    // 어깨 영상
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XT1dHyI86eQ&list=WL&index=3&t=3s"))
                    view.context.startActivity(intent)
                }

                else -> Toast.makeText(context!!, "자세를 선택하여 주세요", Toast.LENGTH_SHORT).show()
            }
        }



        // 시작일 정하기
        start_date_btn.setOnClickListener {
            take_date_start()
            Log.d(MainActivity.TAG, "Current date1: ${start_year} + ${start_month} + ${start_day}")
        }

        // 지정일 정하기
        end_date_btn.setOnClickListener {
            take_date_end()
            Log.d(MainActivity.TAG, "Current date2: ${end_year} + ${end_month} + ${end_day}")
        }

        // 날짜에 따른 조회 버튼
        Search_button.setOnClickListener {

            if ((start_month == null) || (end_month == null) || (start_day == null) || (end_day == null))
            {
                Toast.makeText(context!!, "날짜를 선택하여 주세요", Toast.LENGTH_SHORT).show()

            }else if((start_month!!.toInt() > end_month!!.toInt())){

                Toast.makeText(context!!, "날짜 선택이 올바르지 않습니다", Toast.LENGTH_SHORT).show()

            } else if((start_month!!.toInt() == end_month!!.toInt()) && (start_day!!.toInt() > end_day!!.toInt()))
            {
                Toast.makeText(context!!, "날짜 선택이 올바르지 않습니다", Toast.LENGTH_SHORT).show()
            }
            else {

                //var C = afterDate("$start_year-$start_month-$start_day" , 5 ,"yyyy-MM-dd")
                //Toast.makeText(context!!, "$C", Toast.LENGTH_SHORT).show()
            getDocument()
            ChartDraw()


            Log.d(MainActivity.TAG, "Current date1: ${start_year} + ${start_month} + ${start_day}")
             postion1 = 0
             postion2 = 0
             postion3 = 0
             postion4 = 0
             postion5 = 0
             postion6 = 0
             postion7 = 0
             postion8 = 0

            }
        }


    }


    fun take_date_start() {
        //var calendar: Calendar = Calendar.getInstance()
        var syear = calendar.get(Calendar.YEAR)
        var smonth = calendar.get(Calendar.MONTH)
        var sday = calendar.get(Calendar.DAY_OF_MONTH)


        var listener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
            syear = i
            smonth = i2+1
            sday = i3
            start_year = syear.toString()
            start_month = smonth.toString()
            start_day = sday.toString()
            start_date_btn.text = "${syear}/${smonth}/${sday}"

        }

        var picker = DatePickerDialog(context!!, listener, syear, smonth, sday)
        picker.show()

    }

    fun take_date_end() {
        //var calendar: Calendar = Calendar.getInstance()
        var eyear = calendar.get(Calendar.YEAR)
        var emonth = calendar.get(Calendar.MONTH)
        var eday = calendar.get(Calendar.DAY_OF_MONTH)


        var listener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
            eyear = i
            emonth = i2+1
            eday = i3

            end_date_btn.text = "${eyear}/${emonth}/${eday}"
            end_year = eyear.toString()
            end_month = emonth.toString()
            end_day = eday.toString()

        }

        var picker = DatePickerDialog(context!!, listener, eyear, emonth, eday)
        picker.show()


    }

    fun ChartDraw() {
        chart.setUsePercentValues(true)

        //data set
        val entries = ArrayList<PieEntry>()
        // (데이터 값, 라벨) -> 누적횟수 그리고 자세로 활용
        entries.add(PieEntry(postion1.toFloat(), "postion1"))
        entries.add(PieEntry(postion2.toFloat(), "postion2"))
        entries.add(PieEntry(postion3.toFloat(), "postion3"))
        entries.add(PieEntry(postion4.toFloat(), "postion4"))
        entries.add(PieEntry(postion5.toFloat(), "postion5"))
        entries.add(PieEntry(postion6.toFloat(), "postion6"))
        entries.add(PieEntry(postion7.toFloat(), "postion7"))
        entries.add(PieEntry(postion8.toFloat(), "postion8"))


        //add a lot of colors
        val colorsItems = ArrayList<Int>()
        for(c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for(c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for(c in COLORFUL_COLORS) colorsItems.add(c)
        for(c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for(c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries, "")
            pieDataSet.apply {
                colors = colorsItems
                valueTextColor = Color.BLACK
                valueTextSize = 16f
            }

        val pieData = PieData(pieDataSet)
            chart.apply {
                data = pieData
                description.isEnabled = false
                isRotationEnabled = false
                setEntryLabelColor(Color.BLACK)
                //centerText
                animateY(1400, Easing.EaseInOutQuad)
                animate()
                legend.isEnabled = false
            }



        // 차트에서 조각 선택시 행동
        chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {

                var i = h.x.toInt() + 1
                //System.out.println("Position$i")

                //System.out.println("CategoryName" + Constant.CATEGORY_NAME.get(i))
                //Toast.makeText(context!!, "Position$i", Toast.LENGTH_SHORT).show()

                when(i.toString()){
                    "1" -> {
                        imageView.setImageResource(R.drawable.test_image)
                        position_text.text = "1번 자세입니다."
                        nowState = 1
                    }
                    "2" -> {
                        imageView.setImageResource(R.drawable.graph_icon)
                        position_text.text = "2번 자세입니다."
                        nowState = 1
                    }
                    "3" -> {
                        imageView.setImageResource(R.drawable.main_icon)
                        position_text.text = "3번 자세입니다."
                        nowState = 2
                    }
                    "4" -> {
                        imageView.setImageResource(R.drawable.test_image)
                        position_text.text = "4번 자세입니다."
                        nowState = 2
                    }
                    "5" -> {
                        imageView.setImageResource(R.drawable.test_image)
                        position_text.text = "5번 자세입니다."
                        nowState = 3
                    }
                    "6" -> {
                        imageView.setImageResource(R.drawable.app_logo)
                        position_text.text = "6번 자세입니다."
                        nowState = 3
                    }
                    "7" -> {
                        imageView.setImageResource(R.drawable.main_icon)
                        position_text.text = "7번 자세입니다."
                        nowState = 4
                    }
                    "8" -> {
                        imageView.setImageResource(R.drawable.test_image)
                        position_text.text = "8번 자세입니다."
                        nowState = 4
                    }

                }
                //Toast.makeText(context!!, "x값 : $x, y값 : $y", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected() {}
        })

    }


    private fun afterDate(date: String, day: Int, pattern: String = "yyyy-MM-dd"): String
    { val format = SimpleDateFormat(pattern, Locale.getDefault())
        val calendar = Calendar.getInstance()
        format.parse(date)?.let { calendar.time = it }
        calendar.add(Calendar.DAY_OF_YEAR, day)

        return format.format(calendar.time)
    }

    private fun getDocument() {


        var StartDate = "$start_year-$start_month-$start_day 00:00:00"              //시작 날짜
        var EndDate = "$end_year-$end_month-$end_day 00:00:00"                      // 끝 날짜
        var sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")                    // 패턴
        var first_date = sf.parse(StartDate)
        var last_date = sf.parse(EndDate)
        var calcuDate = (last_date.time - first_date.time) / (60 * 60 * 24 * 1000)  //날짜 차이 구하기



        for (i in 0..calcuDate){
            var CheckDate = afterDate("$start_year-$start_month-$start_day" , i.toInt() ,"yyyy-MM-dd")
            val GetDate = CheckDate.split("-")
            var GetYear = GetDate[0]
            var GetMonth = GetDate[1].toString()
            var GetDay = GetDate[2].toString()

            //Toast.makeText(context!!, "$GetMonth+$GetDay", Toast.LENGTH_SHORT).show()

            val docRef = db.collection("$GetMonth").document("$GetDay")

            docRef.get()
                .addOnSuccessListener { document ->

                    val StartMap = document.data as Map<String, Any>

                    if (document != null) {
                        Log.d("MainActivity", "DocumentSnapshot data: ${document.data}")

                        for((key, value) in StartMap) {
                            when (value.toString()) {

                                "1" -> postion1 += 1
                                "2" -> postion2 += 1
                                "3" -> postion3 += 1
                                "4" -> postion4 += 1
                                "5" -> postion5 += 1
                                "6" -> postion6 += 1
                                "7" -> postion7 += 1
                                "8" -> postion8 += 1
                            }
                        }

                    } else {
                        Log.d("MainActivity", "No such document")
                        //Toast.makeText(this, "데이터 없엉", Toast.LENGTH_SHORT).show()
                    }


                }


                .addOnFailureListener {
                    // Toast.makeText(this, "연결 실패", Toast.LENGTH_SHORT).show()

                }


        }


    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): ChartFragment {
            return ChartFragment()
        }
    }
}
