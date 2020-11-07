package com.example.posturecorrection

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_state.*


// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
const val ACTION_FOO = "com.example.posturecorrection.action.FOO"
const val ACTION_BAZ = "com.example.posturecorrection.action.BAZ"

// TODO: Rename parameters
const val EXTRA_PARAM1 = "com.example.posturecorrection.extra.PARAM1"
const val EXTRA_PARAM2 = "com.example.posturecorrection.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions and extra parameters.
 */
class NotificationService : IntentService("NotificationService") {

    private var NowState = "stable"
    private val db = FirebaseFirestore.getInstance()
    private val NOTIFICATION_ID = 10001

    companion object{

    }

    override fun onHandleIntent(p0: Intent?) {

        // Notification 관련 코드드
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false, getString(R.string.app_name), "App notification channel") // 1

        val channelId = "$packageName-${getString(R.string.app_name)}" // 2
         var title = "자세 측정중입니다"
         var content = "자세 측정중입니다"

        val intent = Intent(baseContext, VideoActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // 3

        val builder = NotificationCompat.Builder(this, channelId)  // 4
        builder.setSmallIcon(R.drawable.app_logo)    // 5

        builder.priority = NotificationCompat.PRIORITY_DEFAULT    // 8
        builder.setAutoCancel(false)   // 9
        builder.setContentIntent(pendingIntent)
        builder.setOngoing(true)
        builder.setContentTitle(title)    // 6
        builder.setContentText(content)    // 7

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11
        Log.d(MainActivity.TAG, "알림 온")

        val docRef = db.collection("user").document("position") // 콜렉션 , 문서 변경

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(MainActivity.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(MainActivity.TAG, "Current data2: ${snapshot.data}")

                val SMap = snapshot.data as Map<String, Any>
                val SP = SMap["now"].toString()

                when(SP) {

                    "1" -> {
                        Log.d(MainActivity.TAG, "1")

                        NowState = "stable"
                        var title1 = "바른 자세입니다"
                        var content1 = "스트레칭 보러가기"
                        //intent.putExtra("state", "stable")
                        builder.setContentTitle(title1)    // 6
                        builder.setContentText(content1)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11
                        startForeground(1 , builder.build())

                    }
                    "2" -> {
                        Log.d(MainActivity.TAG, "2")

                        NowState = "stable"
                        var title1 = "평범한 자세입니다"
                        var content1 = "스트레칭 보러가기"
                        intent.putExtra("state", "stable")
                        builder.setContentTitle(title1)    // 6
                        builder.setContentText(content1)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11
                        startForeground(1 , builder.build())


                    }
                    "3" -> {

                        Log.d(MainActivity.TAG, "3")

                        NowState = "shoulder"
                        var title3 = "정면으로 치우친 자세입니다"
                        var content3 = "스트레칭 보러가기"
                        intent.putExtra("state", "shoulder")
                        builder.setContentTitle(title3)    // 6
                        builder.setContentText(content3)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11
                        startForeground(1 , builder.build())

                    }
                    "4" -> {
                        Log.d(MainActivity.TAG, "4")

                        NowState = "shoulder"
                        var title3 = "걸터 앉아 있는 자세입니다"
                        var content3 = "스트레칭 보러가기"
                        intent.putExtra("state", "shoulder")
                        builder.setContentTitle(title3)    // 6
                        builder.setContentText(content3)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())
                        startForeground(1 , builder.build())
                    }
                    "5" -> {
                        Log.d(MainActivity.TAG, "5")

                        NowState = "waist"
                        var title2 = "좌측으로 치우친 자세입니다"
                        var content2 = "스트레칭 보러가기"
                        intent.putExtra("state", "waist")
                        builder.setContentTitle(title2)    // 6
                        builder.setContentText(content2)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())
                        startForeground(1 , builder.build())
                    }
                    "6" -> {
                        Log.d(MainActivity.TAG, "6")

                        NowState = "waist"
                        var title2 = "우측으로 치우친 자세입니다"
                        var content2 = "스트레칭 보러가기"
                        intent.putExtra("state", "waist")
                        builder.setContentTitle(title2)    // 6
                        builder.setContentText(content2)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11

                        startForeground(1 , builder.build())
                    }
                    "7" -> {
                        Log.d(MainActivity.TAG, "7")

                        NowState = "waist"
                        var title4 = "우측으로 기댄 자세입니다"
                        var content4 = "스트레칭 보러가기"
                        intent.putExtra("state", "waist")
                        builder.setContentTitle(title4)    // 6
                        builder.setContentText(content4)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11
                        startForeground(1 , builder.build())
                    }
                    "8" -> {
                        Log.d(MainActivity.TAG, "8")

                        NowState = "back"
                        var title4 = "좌측으로 기댄 자세입니다"
                        var content4 = "스트레칭 보러가기"
                        intent.putExtra("state", "back")
                        builder.setContentTitle(title4)    // 6
                        builder.setContentText(content4)    // 7
                        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11

                        startForeground(1 , builder.build())
                    }
                }
            } else {
                Log.d(MainActivity.TAG, "Current data: null")
            }
        }

        startForeground(1 , builder.build())
    }


    private fun setBuilder(){

    }

    /*private fun ForeGroundService() {

        val docRef = db.collection("user").document("position") // 콜렉션 , 문서 변경

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(MainActivity.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(MainActivity.TAG, "Current data: ${snapshot.data}")

                val Map = snapshot.data as Map<String, Any>

                when (Map.values.toString()) {

                    "1" -> {
                        NowState = "stable"
                    }
                    "2" -> {
                        NowState = "stable"
                    }
                    "3" -> {
                        NowState = "shoulder"
                    }
                    "4" -> {
                        NowState = "shoulder"
                    }
                    "5" -> {
                        NowState = "waist"
                    }
                    "6" -> {
                        NowState = "waist"
                    }
                    "7" -> {
                        NowState = "back"
                    }
                    "8" -> {
                        NowState = "back"
                    }
                }
            } else {
                Log.d(MainActivity.TAG, "Current data: null")
            }
        }


        // Notification 관련 코드드
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false, getString(R.string.app_name), "App notification channel") // 1

        val channelId = "$packageName-${getString(R.string.app_name)}" // 2
         // var title = "자세 측정중입니다1"
         // var content = "자세 측정중입니다2"

        val intent = Intent(baseContext, VideoActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


        val pendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // 3

        val builder = NotificationCompat.Builder(this, channelId)  // 4
        builder.setSmallIcon(R.drawable.app_logo)    // 5

        builder.priority = NotificationCompat.PRIORITY_DEFAULT    // 8
        builder.setAutoCancel(false)   // 9
        builder.setContentIntent(pendingIntent)

        builder.setOngoing(true)
        when(NowState){
            "stable" -> {
                var title1 = "안정된 자세입니다"
                var content1 = "스트레칭 보러가기"
                intent.putExtra("state", "stable")
                builder.setContentTitle(title1)    // 6
                builder.setContentText(content1)    // 7

            }
            "waist" -> {
                var title2 = "허리 자세입니다"
                var content2 = "스트레칭 보러가기"
                intent.putExtra("state", "waist")
                builder.setContentTitle(title2)    // 6
                builder.setContentText(content2)    // 7
            }
            "shoulder" -> {
                var title3 = "어깨 자세입니다"
                var content3 = "스트레칭 보러가기"
                intent.putExtra("state", "shoulder")
                builder.setContentTitle(title3)    // 6
                builder.setContentText(content3)    // 7
            }
            "back" -> {
                var title4 = "등 자세입니다"
                var content4 = "스트레칭 보러가기"
                intent.putExtra("state", "back")
                builder.setContentTitle(title4)    // 6
                builder.setContentText(content4)    // 7
            }
        }

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID, builder.build())    // 11

        startForeground(1 , builder.build())

    }*/

    // 채널을 만드는 코드
    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean,
                                          name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }




}
