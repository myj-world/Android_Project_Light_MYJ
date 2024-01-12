package com.yousufjamil.myjassistant

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.google.ai.client.generativeai.GenerativeModel
import com.notkamui.keval.Keval
import com.yousufjamil.myjassistant.ui.theme.MYJSmartAssistantTheme
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class MainActivity : ComponentActivity() {

    private var talk by mutableStateOf("")
    private var recording by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.geminiApiKey
        )

        setContent {
            MYJSmartAssistantTheme {
                var msgs = remember {
                    mutableStateListOf<String>()
                }

                var lazyListState = rememberLazyListState()

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
                            0 -> msgs[pos] =
                                "bI'm sorry, but I can't help you with that right now. Please check your network connection. Any inappropriate language may also have caused the error."

                            1 -> msgs[pos] =
                                "bI couldn't connect. Please check your network connection. Any inappropriate language may also have caused the error."

                            2 -> msgs[pos] =
                                "bSorry, we can't seem to connect to the internet right now. Please check your connection and try again. We're waiting here for you! Any inappropriate language may also have caused the error."

                            3 -> msgs[pos] =
                                "bHouston, we have a problem! It seems like you're not connected to the internet. Any inappropriate language may also have caused the error."

                            4 -> msgs[pos] =
                                "bOops! Looks like you've lost your internet connection. Please reconnect to continue. Any inappropriate language may also have caused the error."

                            else -> msgs[pos] =
                                "bUh-oh! It seems like you're not connected to the internet. Please check your connection and try again. Any inappropriate language may also have caused the error."
                        }
                    }
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(msgs.count() - 1)
                    }
                }

                fun reply(msg: String) {
                    Handler().postDelayed(
                        {
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
                                                .contains(" hi ") -> {
                                                "Hi there!"
                                            }

                                            messageLower.lowercase()
                                                .contains("what can you do") -> {
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

                                            messageLower.lowercase()
                                                .contains("what's up") || messageLower.lowercase()
                                                .contains("whats up") || messageLower.lowercase()
                                                .contains("how are you") -> {
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

                                            messageLower.lowercase()
                                                .contains("solve") && !messageLower.lowercase()
                                                .contains("step") -> {
                                                var equation = messageLower.substringAfter("solve")
                                                equation = equation.trim()
                                                try {
                                                    Keval.eval(equation).toString()
                                                } catch (e: Exception) {
                                                    coroutineScope.launch {
                                                        generateResponse(msg)
                                                    }
                                                    "Loading..."
                                                }
                                            }

                                            messageLower.lowercase()
                                                .contains("what") && messageLower.lowercase()
                                                .contains("time") -> {
                                                val timeStamp =
                                                    Timestamp(System.currentTimeMillis())
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
                                                        var number =
                                                            messageLower.substringAfter("call")
                                                        number = number.trim()
                                                        val intent = Intent(Intent.ACTION_CALL)
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
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(msgs.count() - 1)
                            }
                        }, 500
                    )
                }

                var padding by remember {
                    mutableStateOf(0)
                }

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(padding.dp - padding.dp)
                        .background(Color(40, 38, 57))
                ) {
                    Handler().postDelayed(
                        {
                            if (padding <= 0) padding++ else padding--
                            if (!recording && talk != "") {
                                val tempMsg = talk
                                talk = ""
                                msgs.add("u$tempMsg")
                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(msgs.count() - 1)
                                }
                                reply(tempMsg)
                            }
                        }, (if (recording || talk != "") 500 else 1000)
                    )
                    LazyColumn(
                        state = lazyListState,
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
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.8f)
                                            .clip(RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp))
                                            .background(Color(58, 56, 76))
                                            .padding(15.dp)
                                    ) {
                                        Column {
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
                                                            .data(data = R.drawable.load)
                                                            .apply(block = {
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
                                                            color = Color.White,
                                                            fontSize = 16.sp
                                                        ),
                                                        fontResource = R.font.roboto
                                                    )
                                                }
                                                Spacer(modifier = Modifier.height(10.dp))
                                                Divider(modifier = Modifier.fillMaxWidth())
                                                Spacer(modifier = Modifier.height(10.dp))
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .clickable {
                                                            val clipboard =
                                                                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                                            val clip =
                                                                ClipData.newPlainText(
                                                                    "Bot message",
                                                                    msg
                                                                )
                                                            clipboard.setPrimaryClip(clip)
                                                            Toast
                                                                .makeText(
                                                                    this@MainActivity,
                                                                    "Copied to clipboard",
                                                                    Toast.LENGTH_SHORT
                                                                )
                                                                .show()
                                                        },
                                                    horizontalArrangement = Arrangement.Start,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.baseline_content_copy_24),
                                                        contentDescription = "Copy to clipboard",
                                                        tint = Color.White
                                                    )
                                                    Spacer(modifier = Modifier.width(10.dp))
                                                    Text(
                                                        text = "Copy to Clipboard",
                                                        color = Color.White,
                                                        fontSize = 16.sp
                                                    )
                                                }
                                            }
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
                                            .fillMaxWidth(0.8f)
                                            .clip(RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp))
                                            .background(Color(255, 104, 104))
                                            .padding(15.dp)
                                    ) {

                                        Column {
                                            key(msg) {
                                                MarkdownText(
                                                    markdown = msg,
                                                    style = TextStyle(
                                                        color = Color.White,
                                                        fontSize = 16.sp
                                                    ),
                                                    fontResource = R.font.roboto
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Divider(modifier = Modifier.fillMaxWidth())
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clickable {
                                                        val clipboard =
                                                            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                                        val clip =
                                                            ClipData.newPlainText(
                                                                "Your message",
                                                                msg
                                                            )
                                                        clipboard.setPrimaryClip(clip)
                                                        Toast
                                                            .makeText(
                                                                this@MainActivity,
                                                                "Copied to clipboard",
                                                                Toast.LENGTH_SHORT
                                                            )
                                                            .show()
                                                    },
                                                horizontalArrangement = Arrangement.Start,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.baseline_content_copy_24),
                                                    contentDescription = "Copy to clipboard",
                                                    tint = Color.White
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Text(
                                                    text = "Copy to Clipboard",
                                                    color = Color.White,
                                                    fontSize = 16.sp
                                                )
                                            }
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
                                .fillMaxWidth(if (typing == "") 0.6f else 0.8f)
                                .height(120.dp)
                                .clip(
                                    RoundedCornerShape(
                                        25.dp,
                                        0.dp,
                                        0.dp,
                                        25.dp
                                    )
                                ),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(148, 147, 156),
                                focusedContainerColor = Color(148, 147, 156),
                                unfocusedTextColor = Color(83, 81, 97),
                                focusedTextColor = Color(83, 81, 97),
                                unfocusedLabelColor = Color(62, 60, 77),
                                focusedLabelColor = Color(62, 60, 77)
                            )
                        )
                        if (typing == "") {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .height(120.dp)
                                    .clip(

                                        RoundedCornerShape(
                                            0.dp,
                                            0.dp,
                                            0.dp,
                                            0.dp
                                        )
                                    )
                                    .background(Color(126, 125, 136))
                                    .padding(2.dp)
                                    .clickable {
                                        typing = when (Random().nextInt(22)) {
                                            1 -> "What is the population of Pakistan?"
                                            2 -> "What is the capital of Japan?"
                                            3 -> "How do black holes work?"
                                            4 -> "What are the different types of renewable energy?"
                                            5 -> "What is the latest advancement in artificial intelligence?"
                                            6 -> "How do I calculate the area of a circle?"
                                            7 -> "What is the best way to remove a stain from a carpet?"
                                            8 -> "How do I change a light bulb?"
                                            9 -> "What is the recipe for chocolate chip cookies?"
                                            10 -> "Write a short story about a time traveler who visits the past."
                                            11 -> "Write a poem about the beauty of nature."
                                            12 -> "Create a song about a journey of self-discovery."
                                            13 -> "Write a script for a play about a group of friends who are trying to solve a mystery."
                                            14 -> "How can I improve my time management skills?"
                                            15 -> "What is the most efficient way to pack a suitcase for a long trip?"
                                            16 -> "How can I overcome my fear of public speaking?"
                                            17 -> "Who is the author of the Harry Potter series?"
                                            18 -> "What is the most popular video game of all time?"
                                            19 -> "What are the best ways to manage stress?"
                                            20 -> "How can I develop a healthy lifestyle?"
                                            else -> "How can I set and achieve my goals?"
                                        }
                                    }
                                    .height(50.dp),
                                contentAlignment = Alignment.Center

                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.baseline_lightbulb_24
                                    ),
                                    contentDescription = "Suggest",
                                    tint = Color.White,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(

                                    RoundedCornerShape(
                                        0.dp,
                                        25.dp,
                                        25.dp,
                                        0.dp
                                    )
                                )
                                .background(Color(105, 103, 116))
                                .padding(2.dp)
                                .clickable {
                                    if (typing != "") {
                                        msgs.add("u$typing")
                                        val tempMsg = typing
                                        typing = ""
                                        coroutineScope.launch {
                                            lazyListState.animateScrollToItem(msgs.count() - 1)
                                        }
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
                                            coroutineScope.launch {
                                                lazyListState.animateScrollToItem(msgs.count() - 1)
                                            }
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