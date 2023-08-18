package com.yousufjamil.myjchitchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.auth.FirebaseAuth
import com.yousufjamil.myjchitchat.ui.theme.MYJChitChatTheme

lateinit var firebaseAuth: FirebaseAuth
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJChitChatTheme {
                
            }
        }
    }
}