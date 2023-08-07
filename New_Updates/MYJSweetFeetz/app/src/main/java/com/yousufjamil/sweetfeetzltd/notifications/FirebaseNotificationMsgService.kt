package com.yousufjamil.sweetfeetzltd.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.yousufjamil.sweetfeetzltd.MainActivity
import com.yousufjamil.sweetfeetzltd.R
//import com.yousufjamil.sweetfeetzltd.constants.FIREBASE_TOKEN
import kotlin.random.Random

//private const val CHANNEL_ID = "myj-sweet_feetz"

class FirebaseNotificationMsgService: FirebaseMessagingService() {
//    override fun onNewToken(token: String) {
//        println("Token is: $token")
//
//        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
//        preferences.edit().putString(FIREBASE_TOKEN, token).apply()
//        super.onNewToken(token)
//    }
//    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
//
//        val intent = Intent(this, MainActivity::class.java)
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationId = Random.nextInt()
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createNotificationChannel(notificationManager)
//        }
//
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
//            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle(message.data["title"])
//            .setContentText(message.data["message"])
//            .setSmallIcon(R.drawable.app_ic)
//            .setAutoCancel(true)
//            .setContentIntent(pendingIntent)
//            .build()
//
//        notificationManager.notify(notificationId, notification)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createNotificationChannel(notificationManager: NotificationManager) {
//        val channelName = "MYJSweetFeetz"
//        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_LOW).apply {
//            description = "MYJSweetFeetz"
//            enableLights(true)
////            lightColor = Color.Green
//        }
//        notificationManager.createNotificationChannel(channel)
//    }
}