package com.yousufjamil.sweetfeetzltd.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.yousufjamil.sweetfeetzltd.MainActivity
import com.yousufjamil.sweetfeetzltd.R


class FirebaseNotificationInstanceIDService: FirebaseMessagingService() {
//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onMessageReceived(message: RemoteMessage) {
//        sendMyNotification(message.notification!!.body)
//    }
//
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun sendMyNotification(message: String?) {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
//            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
//        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this)
//            .setSmallIcon(R.drawable.app_ic)
//            .setContentTitle("My Firebase Push notification")
//            .setContentText(message)
//            .setAutoCancel(true)
//            .setSound(soundUri)
//            .setContentIntent(pendingIntent)
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(0, notificationBuilder.build())
//    }
}