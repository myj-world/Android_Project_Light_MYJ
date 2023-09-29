package com.yousufjamil.mailsendertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yousufjamil.mailsendertest.ui.theme.MailSenderTestTheme
import net.axay.simplekotlinmail.email.emailBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MailSenderTestTheme {
                val scope = rememberCoroutineScope()
                Button(onClick = {
                    val email = emailBuilder {
                        from("foo@bar.com")
                        to("info@example.org")

                        withSubject("Important question")
                        withPlainText("Hey, how are you today?")

                        // and much more
                    }
                }) {
                    Text(text = "Send email")
                }
            }
        }
    }
}