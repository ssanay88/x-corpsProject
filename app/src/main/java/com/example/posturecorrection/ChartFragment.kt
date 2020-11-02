package com.example.posturecorrection

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_chart.*
import java.util.*

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
    private var param1: String? = null
    private var param2: String? = null

    // 시작 년 월 일
    private var start_year: String? = null
    private var start_month: String? = null
    private var start_day: String? = null

    // 끝 년 월 일
    private var end_year: String? = null
    private var end_month: String? = null
    private var end_day: String? = null

    var cal = Calendar.getInstance()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            Log.d(MainActivity.TAG, "Current date1: ${start_year} + ${start_month} + ${start_day}")
            toggleButton.clearChecked()     //토글 버튼 해제
        }

        // 토글 버튼 중 하루 동안 데이터
        day_button.setOnClickListener {
            Log.d(MainActivity.TAG, "Current date2: ${start_year} + ${start_month} + ${start_day}")
        }
        // 토글 버튼 중 일주일 동안 데이터
        week_button.setOnClickListener {
            Log.d(MainActivity.TAG, "Current date3: ${start_year} + ${start_month} + ${start_day}")
        }
        // 토글 버튼 중 한달 동안 데이터
        month_button.setOnClickListener {
            Log.d(MainActivity.TAG, "Current date4: ${start_year} + ${start_month} + ${start_day}")
        }

    }


    fun take_date_start() {
        var calendar: Calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)


        var listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            year = i
            month = i2+1
            day = i3
            start_date_btn.text = "${year}/${month}/${day}"

        }

        var picker = DatePickerDialog(context!!, listener, year, month, day)
        picker.show()
        start_year = year.toString()
        start_month = month.toString()
        start_day = day.toString()

    }

    fun take_date_end() {
        var calendar: Calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)


        var listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            year = i
            month = i2+1
            day = i3
            end_date_btn.text = "${year}/${month}/${day}"

        }

        var picker = DatePickerDialog(context!!, listener, year, month, day)
        picker.show()
        end_year = year.toString()
        end_month = month.toString()
        end_day = day.toString()

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