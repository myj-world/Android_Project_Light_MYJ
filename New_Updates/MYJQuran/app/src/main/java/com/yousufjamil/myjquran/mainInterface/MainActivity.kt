package com.yousufjamil.myjquran.mainInterface

import Quran
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yousufjamil.myjquran.R
import com.yousufjamil.myjquran.ui.theme.MYJQuranTheme
import kotlinx.serialization.Serializable
import org.json.JSONArray

var surah = 0

class MainActivity : ComponentActivity() {

    lateinit var navigator: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJQuranTheme {
                navigator = rememberNavController()
                Navigation(navigator = navigator, context = this@MainActivity)
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun Previewer() {
//        Text(text = "Previewer")
        navigator = rememberNavController()
        Navigation(navigator = navigator, context = this)
    }
}

@Composable
fun Navigation(navigator: NavHostController, context: Context) {
    NavHost(navController = navigator, startDestination = HomeScreen) {
        composable<HomeScreen> {
            val surahs = JSONArray(Quran().surahs)

            val uthmaniFont = FontFamily(
                Font(resId = R.font.uthmani, weight = FontWeight.Normal)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(46, 49, 54, 255))
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Qur'an",
                    fontSize = 35.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(15.dp))
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Choose a surah:",
                    fontSize = 25.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color(232, 233, 235, 255)
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    for (i in 0..113) {
                        item {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(66, 69, 74, 255))
                                    .clickable {
                                        surah = surahs
                                            .getJSONObject(i)
                                            .getInt("id")
                                            .minus(1)
                                        navigator.navigate(SurahScreen)
                                    }
                                    .padding(horizontal = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Surah ${
                                        surahs.getJSONObject(i).getString("transliteration")
                                    }",
                                    fontSize = 20.sp,
                                    color = Color(168, 170, 173, 255)
                                )
                                Text(
                                    text = "سورة ${surahs.getJSONObject(i).getString("name")}",
                                    fontSize = 20.sp,
                                    color = Color(168, 170, 173, 255),
                                    fontFamily = uthmaniFont
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
        composable<SurahScreen> {
            val surahs = JSONArray(Quran().surahs)

            val uthmaniFont = FontFamily(
                Font(resId = R.font.uthmani, weight = FontWeight.Normal)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(46, 49, 54, 255))
                    .padding(40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Surah ${surahs.getJSONObject(surah).getString("transliteration")}",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "سورة ${surahs.getJSONObject(surah).getString("name")}",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                val verses = surahs.getJSONObject(surah).getInt("total_verses").minus(1)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .fillMaxWidth()
                ) {
                    for (i in 0..verses) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = surahs.getJSONObject(surah).getJSONArray("verses")
                                        .getJSONObject(i).getString("text"),
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f),
                                    textAlign = TextAlign.Right,
                                    color = Color.White,
                                    fontFamily = uthmaniFont
                                )
                                Spacer(modifier = Modifier.width(10.dp))

                                val ayah = surahs.getJSONObject(surah).getJSONArray("verses").getJSONObject(i).getInt("id").toString().replace("1","١").replace("2","٢").replace("3","٣").replace("4","٤").replace("5","٥").replace("6","٦").replace("7","٧").replace("8","٨").replace("9","٩").replace("0","٠")
                                Text(
                                    text = ayah,
                                    fontSize = 25.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Right,
                                    color = Color.White,
                                    fontFamily = uthmaniFont
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Text from risan/quran-json from ")
                        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("GitHub")
                        }
                    },
                    modifier = Modifier
                        .clickable {
                            val intent = Intent()
                            intent.setData(Uri.parse("https://github.com/risan/quran-json?tab=readme-ov-file"))
                            intent.action = Intent.ACTION_VIEW
                            context.startActivity(intent)
                        },
                    color = Color.Gray
                )
            }
        }
    }
}

@Serializable
object HomeScreen

@Serializable
object SurahScreen