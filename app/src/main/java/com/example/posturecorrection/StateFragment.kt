package com.example.posturecorrection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_state.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }






    // 프래그먼트에 표시할 뷰를 레이아웃 파일로부터 읽어오는 부분 - fragment_state.xml파일을 가져온다
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_state, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val docRef = db.collection("user").document("position") // 콜렉션 , 문서 변경

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(MainActivity.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(MainActivity.TAG, "Current data: ${snapshot.data}")
                // 값의 변화에 따라서 변경
                // stateFragment 변경 -> 알림창도 변경시켜야함

                val Map = snapshot.data as Map<String, Any>
                val P = Map["now"].toString()
                when(P){

                    "1" -> {
                        now_image.setImageResource(R.drawable.video_icon)
                        message.text = "1번 자세 입니다."
                        Myapplication.prefs.setString("state","stable")

                    }
                    "2" ->
                    {
                        now_image.setImageResource(R.drawable.main_icon)
                        message.text = "2번 자세 입니다."
                        Myapplication.prefs.setString("state","stable")

                    }
                    "3" ->
                    {
                        now_image.setImageResource(R.drawable.graph_icon)
                        message.text = "3번 자세 입니다."
                        Myapplication.prefs.setString("state","shoulder")

                    }
                    "4" ->
                    {
                        now_image.setImageResource(R.drawable.app_logo)
                        message.text = "4번 자세 입니다."
                        Myapplication.prefs.setString("state","shoulder")

                    }
                    "5" ->
                    {
                        now_image.setImageResource(R.drawable.video_icon)
                        message.text = "5번 자세 입니다."
                        Myapplication.prefs.setString("state","back")
                    }
                    "6" ->
                    {
                        now_image.setImageResource(R.drawable.main_icon)
                        message.text = "6번 자세 입니다."
                        Myapplication.prefs.setString("state","back")

                    }
                    "7" ->
                    {
                        now_image.setImageResource(R.drawable.app_logo)
                        message.text = "7번 자세 입니다."
                        Myapplication.prefs.setString("state","waist")

                    }
                    "8" ->
                    {
                        now_image.setImageResource(R.drawable.test_image)
                        message.text = "8번 자세 입니다."
                        Myapplication.prefs.setString("state","waist")

                    }
                }
            } else {
                Log.d(MainActivity.TAG, "Current data: null")
            }
        }
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() : StateFragment {
            return StateFragment()
        }

    }
}