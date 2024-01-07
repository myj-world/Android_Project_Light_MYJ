package com.yousufjamil.myjassistant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
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
import androidx.compose.runtime.Composable
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
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL
import com.google.android.exoplayer2.Player.REPEAT_MODE_OFF
import com.google.android.exoplayer2.ui.StyledPlayerView
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var talk by mutableStateOf("")
    private var recording by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.geminiApiKey
        )

//        Toast.makeText(this, "Sending a message may take some time", Toast.LENGTH_SHORT).show()

        setContent {
            MYJSmartAssistantTheme {
                var msgs = remember {
                    mutableStateListOf<String>()
                }

                val coroutineScope = rememberCoroutineScope()

                suspend fun generateResponse(msg: String) {
                    val pos = msgs.count() - 1
                    try {
                        val response = generativeModel.generateContent(msg)
                        if (response.text != null) {
                            println("Tests: ${response.text}")
                            msgs[pos] = "b${response.text.toString()}"
                        } else {
                            throw Exception()
                        }
                    } catch (e: Exception) {
                        when (Random().nextInt(6)) {
                            0 -> msgs[pos] = "bI'm sorry, but I can't help you with that right now. Please check your network connection. Any inappropriate language may also have caused the error."
                            1 -> msgs[pos] = "bI couldn't connect. Please check your network connection. Any inappropriate language may also have caused the error."
                            2 -> msgs[pos] = "bSorry, we can't seem to connect to the internet right now. Please check your connection and try again. We're waiting here for you! Any inappropriate language may also have caused the error."
                            3 -> msgs[pos] = "bHouston, we have a problem! It seems like you're not connected to the internet. Any inappropriate language may also have caused the error."
                            4 -> msgs[pos] = "bOops! Looks like you've lost your internet connection. Please reconnect to continue. Any inappropriate language may also have caused the error."
                            else -> msgs[pos] = "bUh-oh! It seems like you're not connected to the internet. Please check your connection and try again. Any inappropriate language may also have caused the error."
                        }
                    }
                }

                fun reply(msg: String) {
                    msgs.add(
                        "b${
                            try {
                                val messageLower = msg.lowercase()
                                when {
                                    messageLower.lowercase().contains("salam") -> {
                                        "Walaikumassalam"
                                    }

                                    messageLower.lowercase()
                                        .contains("hello") || messageLower.lowercase()
                                        .contains("hi") -> {
                                        "Hi there!"
                                    }

                                    messageLower.lowercase()
                                        .contains("what") && messageLower.lowercase()
                                        .contains("do") -> {
                                        "I can do a lot of things: \n" +
                                                "- Respond to your questions with AI \n" +
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

                                    (messageLower.lowercase()
                                        .contains("what") && messageLower.lowercase()
                                        .contains("up")) || (messageLower.lowercase()
                                        .contains("how") && messageLower.lowercase()
                                        .contains("you")) -> {
                                        "I'm doing great, thanks for asking!"
                                    }

                                    messageLower.lowercase()
                                        .contains("flip") && messageLower.lowercase()
                                        .contains("coin") -> {
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
                                        var searchTerm: String =
                                            messageLower.substringAfter("search")
                                        searchTerm = searchTerm.trim()
                                        site.data =
                                            Uri.parse("https://www.google.com/search?&q=$searchTerm")
                                        startActivity(site)

                                        "Searching..."
                                    }

                                    messageLower.lowercase()
                                        .contains("make") && messageLower.lowercase()
                                        .contains("tea") -> {
                                        "Here's your tea: ☕ \n" +
                                                "Hope you enjoy it 😉"
                                    }

                                    messageLower.lowercase().contains("call") -> {
                                        try {

                                            if (ContextCompat.checkSelfPermission(
                                                    this,
                                                    android.Manifest.permission.CALL_PHONE
                                                ) != PackageManager.PERMISSION_GRANTED
                                            ) {
                                                ActivityCompat.requestPermissions(
                                                    this,
                                                    arrayOf(android.Manifest.permission.CALL_PHONE),
                                                    101
                                                )
                                            }

                                            if (ContextCompat.checkSelfPermission(
                                                    this,
                                                    android.Manifest.permission.CALL_PHONE
                                                ) == PackageManager.PERMISSION_GRANTED
                                            ) {
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
                                        coroutineScope.launch {
                                            generateResponse(msg)
                                        }
                                        "Loading..."
                                    }
                                }
                            } catch (e: Exception) {
                                when (Random().nextInt(6)) {
                                    0 -> "I'm sorry, but I can't help you with that right now. Please check your network connection."
                                    1 -> "I couldn't connect. Please check your network connection."
                                    2 -> "Sorry, we can't seem to connect to the internet right now. Please check your connection and try again. We're waiting here for you!"
                                    3 -> "Houston, we have a problem! It seems like you're not connected to the internet."
                                    4 -> "Oops! Looks like you've lost your internet connection. Please reconnect to continue."
                                    else -> "Uh-oh! It seems like you're not connected to the internet. Please check your connection and try again."
                                }
                            }
                        }"
                    )
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
                            if (!recording && talk != "") {
                                val tempMsg = talk
                                talk = ""
                                msgs.add("u$tempMsg")
                                reply(tempMsg)
                            }
                        }, (if (recording || talk != "") 500 else 1000)
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
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {}
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp))
                                        .background(Color(26, 26, 26))
                                        .padding(20.dp)
                                ) {
//                                    Text(
//                                        text = msg,
//                                        color = Color.White
//                                    )
                                    if (msg == "Loading...") {
                                        val context = LocalContext.current
                                        val imageLoader = ImageLoader.Builder(context)
                                            .components {
                                                if (SDK_INT >= 28) {
                                                    add(ImageDecoderDecoder.Factory())
                                                } else {
                                                    add(GifDecoder.Factory())
                                                }
                                            }
                                            .build()
                                        Image(
                                            painter = rememberAsyncImagePainter(
                                                ImageRequest.Builder(context)
                                                    .data(data = R.drawable.load).apply(block = {
                                                    size(Size.ORIGINAL)
                                                }).build(), imageLoader = imageLoader
                                            ),
                                            contentDescription = "Loading...",
                                            modifier = Modifier.fillMaxWidth(),
                                        )
                                    } else {
                                        key(msg) {
                                            MarkdownText(
                                                markdown = msg,
                                                style = TextStyle(
                                                    color = Color.White
                                                ),
                                                fontResource = R.font.poppins
                                            )
                                        }
                                    }
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
//                                        Text(
//                                            text = msg,
//                                            color = Color.White
//                                        )

                                        key(msg) {
                                            MarkdownText(
                                                markdown = msg,
                                                style = TextStyle(
                                                    color = Color.White
                                                ),
                                                fontResource = R.font.poppins
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        for (i in 0..msgs.size) {
                            if (i == 0 && msgs.size == 0) {
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
                                if (msgs[i].substring(0, 1) == "u") {
                                    addMsgUser(msgs[i].substring(1, msgs[i].length))
                                }
                                if (msgs[i].substring(0, 1) == "b") {
                                    addMsgBot(msgs[i].substring(1, msgs[i].length))
                                }
                            }
                        }
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
                        Box(
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
                                        val tempMsg = typing
//                                        userMsgs.add(typing)
//                                        botMsgs.add(reply(typing))
                                        typing = ""
                                        reply(tempMsg)
                                    } else {
                                        if (ContextCompat.checkSelfPermission(
                                                this@MainActivity,
                                                android.Manifest.permission.RECORD_AUDIO
                                            ) != PackageManager.PERMISSION_GRANTED
                                        ) {
                                            ActivityCompat.requestPermissions(
                                                this@MainActivity,
                                                arrayOf(android.Manifest.permission.RECORD_AUDIO),
                                                103
                                            )
                                        }

                                        if (ContextCompat.checkSelfPermission(
                                                this@MainActivity,
                                                android.Manifest.permission.RECORD_AUDIO
                                            ) == PackageManager.PERMISSION_GRANTED
                                        ) {
                                            SpeechToText(this@MainActivity)
                                        } else {
                                            msgs.add("bPermission not granted, please grant through settings")
                                        }
                                    }
                                }
                                .height(50.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            Icon(
                                painter = if (typing != "") painterResource(id = R.drawable.baseline_send_24) else painterResource(
                                    id = R.drawable.baseline_mic_24
                                ),
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

    fun SpeechToText(context: Context) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            Toast.makeText(context, "Speech not Available", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Talk Something")

            startActivityForResult(intent, 102)

            recording = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            talk = result?.get(0).toString()
            recording = false
        }
    }
}