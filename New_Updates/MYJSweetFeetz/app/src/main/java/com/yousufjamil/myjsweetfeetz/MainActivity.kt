package com.yousufjamil.myjsweetfeetz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yousufjamil.myjsweetfeetz.ui.theme.MYJSweetFeetzTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJSweetFeetzTheme {
                val navController = rememberNavController()
                Scaffold (
                    modifier = Modifier.padding(0.dp),
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Design",
                                    route = "design",
                                    icon = Icons.Default.Add
                                ),
                                BottomNavItem(
                                    name = "Feedback",
                                    route = "feedback",
                                    icon = Icons.Default.Create
                                ),
                                BottomNavItem(
                                    name = "Profile",
                                    route = "profile",
                                    icon = Icons.Default.Person
                                )
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation(navController = navController)
                    Box(
                        modifier = Modifier.padding(it),
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("design") {
            DesignScreen()
        }
        composable("feedback") {
            FeedbackScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xFFb7ceff),
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF0e5afb),
                    unselectedIconColor = Color(0xFF75859e),
                    indicatorColor = Color(0xFFb7ceff)
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(48.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SWEET FEETZ LTD",
                color = Color(0xFF000000),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3b70e0)
                ),
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3b70e0)
                ),
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = "Log in")
            }
        }
    }
}

@Composable
fun DesignScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Design Screen")
    }
}

@Composable
fun FeedbackScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Feedback Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen")
    }
}