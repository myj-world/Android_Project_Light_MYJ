package com.yousufjamil.myjchitchat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.yousufjamil.myjchitchat.navandtopbar.AppTopBar
import com.yousufjamil.myjchitchat.navandtopbar.MenuItem
import com.yousufjamil.myjchitchat.ui.theme.MYJChitChatTheme
import kotlinx.coroutines.launch

lateinit var firebaseAuth: FirebaseAuth

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJChitChatTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                    Scaffold(
                        topBar = {
                            AppTopBar(
                                onNavigationIconClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            )
                        }
                    ) {
                        Box(modifier = Modifier.padding(it))
                        ModalNavigationDrawer(
                            drawerContent = {
                                DrawerBody(
                                    items = listOf(
                                        MenuItem("home", "Home", Icons.Default.Home, "Home icon"),
                                        MenuItem("test1", "Test1", Icons.Default.Home, "Home icon"),
                                        MenuItem("test2", "Test2", Icons.Default.Home, "Home icon"),
                                        MenuItem("test3", "Test3", Icons.Default.Home, "Home icon"),
                                        MenuItem("test4", "Test4", Icons.Default.Home, "Home icon"),
                                        MenuItem("test5", "Test5", Icons.Default.Home, "Home icon"),
                                        MenuItem("test6", "Test6", Icons.Default.Home, "Home icon")
                                    ),
                                    onItemClick = { item ->
                                        Toast.makeText(this@MainActivity, "Clicked ${item.title}", Toast.LENGTH_SHORT).show()
                                    }
                                )
                            },
                            drawerState = drawerState
                        ) {
                            Text(text = "Testing...")
                        }
                    }
                    }
        }
    }
}

//@Composable
//fun DrawerHeader() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 64.dp)
//            .background(Color(0xFFFF0000)),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Header", fontSize = 64.sp)
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBody(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    var selected by remember { mutableStateOf("") }
    Column {
        items.forEach { item ->
            NavigationDrawerItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.contentDescription) },
                label = { Text(text = item.title) },
                selected = item.title == selected,
                onClick = {
                    onItemClick(item)
                    selected = item.title
                }
            )
        }
    }
}