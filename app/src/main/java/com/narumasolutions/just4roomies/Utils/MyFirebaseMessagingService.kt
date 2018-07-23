package com.narumasolutions.just4roomies.Utils

import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService
import com.narumasolutions.just4roomies.R
import android.graphics.BitmapFactory




class MyFirebaseMessagingService : FirebaseMessagingService() {


    companion object {
        private val TAG = "MyFirebaseMsgService"
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage!!.from!!)

        // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow()
            }

        }

        remoteMessage.notification!!.body?.let { sendNotification(it) }


        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    // [START on_new_token]
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: " + token!!)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }
    // [END on_new_token]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private fun scheduleJob() {
        // [START dispatch_job]
        /*val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(this))
        val myJob = dispatcher.newJobBuilder()
                .setService(MyJobService::class.java)
                .setTag("my-job-tag")
                .build()
        dispatcher.schedule(myJob)*/
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, token)
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(messageBody: String) {

        val icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val channelId = getString(R.string.channelID)


        val mBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_app_notification)
                .setContentTitle(getString(R.string.app_name))
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher))
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setContentText(messageBody)
                .setStyle(NotificationCompat.BigTextStyle().bigText(messageBody))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }


        notificationManager.notify(0,mBuilder.build())



    }
}