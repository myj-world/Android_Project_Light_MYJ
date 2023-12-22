package com.yousufjamil.myjassistant

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.notkamui.keval.Keval
import com.yousufjamil.myjassistant.ui.theme.MYJSmartAssistantTheme
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJSmartAssistantTheme {
                var msgs = remember {
                    mutableStateListOf<String>()
                }

                var botMsgs = remember {
                    mutableStateListOf<String>()
                }
                var userMsgs = remember {
                    mutableStateListOf<String>()
                }
                var alternate by remember {
                    mutableStateOf(0)
                }

                fun reply(msg: String) : String {
                    try {
                        val messageLower = msg.lowercase()
                    return when {
                        messageLower.lowercase().contains("salam") -> {
                            "Walaikumassalam"
                        }
                        messageLower.lowercase().contains("hello") || messageLower.lowercase().contains("hi") -> {
                            "Hi there!"
                        }
                        messageLower.lowercase().contains("what") &&  messageLower.lowercase().contains("do")-> {
                            "I can do a lot of things: \n" +
                                    "- Respond to 'hello' or 'Salam' greeting \n" +
                                    "- Respond to 'What's up' or 'how are you' \n" +
                                    "- Flip a coin \n" +
                                    "- Solve difficult maths sums for you \n" +
                                    "- Tell you the time \n" +
                                    "- Open google for you \n" +
                                    "- Search google for you \n" +
                                    "- Make a phone call \n" +
                                    "- Make tea for you"
                        }
                        (messageLower.lowercase().contains("what") && messageLower.lowercase().contains("up")) || (messageLower.lowercase().contains("how") && messageLower.lowercase().contains("you"))-> {
                            "I'm doing great, thanks for asking!"
                        }
                        messageLower.lowercase().contains("flip") && messageLower.lowercase().contains("coin") -> {
                            when (Random().nextInt(2)) {
                                0 -> "Coin landed on: Head"
                                else -> "Coin landed on: Tail"
                            }
                        }
                        messageLower.lowercase().contains("solve") -> {
                            var equation = messageLower.substringAfter("solve")
                            equation = equation.trim()
                            try {
                                Keval.eval(equation).toString()
                            } catch (e: Exception) {
                                "Sorry, but I can't solve that!"
                            }
                        }
                        messageLower.lowercase().contains("time") -> {
                            val timeStamp = Timestamp(System.currentTimeMillis())
                            val sdf = SimpleDateFormat("HH:mm")
                            val time = sdf.format(Date(timeStamp.time))

                            time.toString()
                        }
                        messageLower.lowercase().contains("open google") -> {
                            val site = Intent(Intent.ACTION_VIEW)
                            site.data = Uri.parse("https://www.google.com")
                            startActivity(site)

                            "Opening Google..."
                        }
                        messageLower.lowercase().contains("search") -> {
                            val site = Intent(Intent.ACTION_VIEW)
                            var searchTerm: String = messageLower.substringAfter("search")
                            searchTerm = searchTerm.trim()
                            site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                            startActivity(site)

                            "Searching..."
                        }
                        messageLower.lowercase().contains("make") && messageLower.lowercase().contains("tea") -> {
                            "Here's your tea: ☕ \n" +
                                    "Hope you enjoy it 😉"
                        }
                        messageLower.lowercase().contains("call") -> {
                            try {

                                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 101)
                                }

                                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    var number = messageLower.substringAfter("call")
                                    number = number.trim()
                                    val intent = Intent(Intent.ACTION_CALL);
                                    intent.data = Uri.parse("tel:$number")
                                    startActivity(intent)
                                    "Calling..."
                                } else {
                                    "Permission not granted, please grant through settings"
                                }
                            } catch (e: Exception) {
                                "Advice: Please enter a valid number" +
                                        "Error is: $e"
                            }
                        }
//                        messageLower.lowercase().contains("add") && messageLower.lowercase().contains("event") -> {
//                            "Adding..."
//                        }
                        else -> {
                            when (Random().nextInt(3)) {
                                0 -> "I'm sorry, but I can't help you with that."
                                1 -> "I don't know about it"
                                else -> "I'm sorry, I'm unsure of your request"
                            }
                        }
                    }
                    } catch (e: Exception) {
                        return "Error: $e"
                    }
                }

                var padding by remember {
                    mutableStateOf(0)
                }

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(padding.dp - padding.dp)
                ) {
                    Handler().postDelayed(
                        {
                        if (padding <= 0) padding++ else padding--
                        }, 5000
                    )
                    LazyColumn(
                        state = rememberLazyListState(),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.9f)
                    ) {
                        fun addMsgBot(msg: String) {
                            item {
                                Box (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {}
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp))
                                        .background(Color(68, 68, 68))
                                        .padding(20.dp)
                                ) {
                                    Text(
                                        text = msg,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        fun addMsgUser(msg: String) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp))
                                            .background(Color(124, 148, 180))
                                            .padding(20.dp)
                                    ) {
                                        Text(
                                            text = msg,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }

                        for (i in 0..msgs.size) {
                            if (i==0 && msgs.size == 0) {
                                val name = when (Random().nextInt(15)) {
                                    0 -> "Farhan"
                                    1 -> "Yousuf"
                                    2 -> "Hussain"
                                    3 -> "Hamzah"
                                    4 -> "Bilal"
                                    5 -> "Amir"
                                    6 -> "Abdullah"
                                    7 -> "Sameer"
                                    8 -> "Ahsan"
                                    9 -> "Muhammad"
                                    10 -> "Khalid"
                                    11 -> "Saleem"
                                    12 -> "Abdul Rafay"
                                    13 -> "Abdul Moheed"
                                    else -> "Usman"
                                }
                                msgs.add("bAssalamoalaikum, you are talking to $name")
                            }
                            if (msgs.size > i) {
                                if (msgs[i].substring(0,1) == "u") {
                                    addMsgUser(msgs[i].substring(1, msgs[i].length))
                                }
                                if (msgs[i].substring(0,1) == "b") {
                                    addMsgBot(msgs[i].substring(1, msgs[i].length))
                                }
                            }
                        }

//                            for (i in 0..botMsgs.size) {
//                                if (i==0 && botMsgs.size == 0) botMsgs.add("Assalamoalaikum, you are talking to name")
//                                if (userMsgs.size >= i-1) {
//                                    if (i-2 >= 0) addMsgUser(userMsgs[i-2])
//                                }
//                                if (botMsgs.size >= i-1) {
//                                    addMsgBot(botMsgs[i-1])
//                                }
////                                try {
//////                                alternate = if (alternate == 0) {
//////                                    addMsgBot(botMsgs[i])
//////                                    1
//////                                } else {
//////                                    addMsgUser(userMsgs[i])
//////                                    0
//////                                }
////
////                                } catch (e: Exception) {
////                                    println(e)
////                                }
//                            }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(10.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        var typing by remember {
                            mutableStateOf("")
                        }
                        TextField(
                            value = typing,
                            onValueChange = {
                                typing = it
                            },
                            maxLines = 3,
                            label = {
                                Text(text = "Message")
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .clip(
                                    RoundedCornerShape(
                                        25.dp
                                    )
                                )
                        )
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        25.dp
                                    )
                                )
                                .background(Color(12, 59, 131))
                                .padding(2.dp)
                                .clickable {
                                    if (typing != "") {
                                        msgs.add("u$typing")
                                        msgs.add("b${reply(typing)}")
//                                        userMsgs.add(typing)
//                                        botMsgs.add(reply(typing))
                                        typing = ""
                                    } else {
                                        Toast
                                            .makeText(
                                                this@MainActivity,
                                                "Please type something",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }
                                .height(50.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Send",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}