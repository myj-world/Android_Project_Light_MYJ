package com.yousufjamil.myjpasswordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yousufjamil.myjpasswordgenerator.ui.theme.MYJPasswordGeneratorTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJPasswordGeneratorTheme {
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = "Background",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var length by remember {
                        mutableStateOf("")
                    }
                    var password by remember {
                        mutableStateOf("")
                    }
                    if (password.length !in 1..99) {
                        TextField(
                            value = length,
                            onValueChange = {
                                length = it
                            },
                            label = {
                                Text(text = "Length")
                            }
                        )
                        Button(onClick = {
                            try {
                                if (length.toInt() in 1..99) {
                                    password = GeneratePassword(length.toInt())
                                } else {
                                    throw Exception()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Length should be a number greater than 0 and less than or equal to 99",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }) {
                            Text(text = "Generate Password")
                        }
                    } else {
                        Row (
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth()
                                .background(Color(255,255,255,255))
                                .clip(RoundedCornerShape(50.dp))
                                .padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = password,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            IconButton(onClick = {
                                val clipboard =
                                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Password", password)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(
                                    this@MainActivity,
                                    "Copied to clipboard",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_content_copy_24),
                                    contentDescription = "Copy to Clipboard",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        Button(onClick = {
                            password = ""
                        }) {
                            Text(text = "Back")
                        }
                    }
                }
            }
        }

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }
}

fun GeneratePassword(length: Int): String {
    var password = ""
    for (i in 1..length) {
        val random = Random().nextInt(42)
        var char: String
        when (random) {
            0 -> char = "0"
            1 -> char = "1"
            2 -> char = "2"
            3 -> char = "3"
            4 -> char = "4"
            5 -> char = "5"
            6 -> char = "6"
            7 -> char = "7"
            8 -> char = "8"
            9 -> char = "9"
            10 -> char = "a"
            11 -> char = "b"
            12 -> char = "c"
            13 -> char = "d"
            14 -> char = "e"
            15 -> char = "f"
            16 -> char = "g"
            17 -> char = "h"
            18 -> char = "i"
            19 -> char = "j"
            20 -> char = "k"
            21 -> char = "l"
            22 -> char = "m"
            23 -> char = "n"
            24 -> char = "o"
            25 -> char = "p"
            26 -> char = "q"
            27 -> char = "r"
            28 -> char = "s"
            29 -> char = "t"
            30 -> char = "u"
            31 -> char = "v"
            32 -> char = "w"
            33 -> char = "x"
            34 -> char = "y"
            35 -> char = "z"
            36 -> char = "!"
            37 -> char = "@"
            38 -> char = "#"
            39 -> char = "$"
            40 -> char = "&"
            else -> char = "*"
        }
        password += char
    }
    return password
}