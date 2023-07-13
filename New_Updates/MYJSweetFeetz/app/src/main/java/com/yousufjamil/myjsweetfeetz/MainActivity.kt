package com.yousufjamil.myjsweetfeetz

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.yousufjamil.myjsweetfeetz.ui.theme.MYJSweetFeetzTheme

lateinit var firebaseAuth: FirebaseAuth
lateinit var navController: NavHostController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        setContent {
            var otherScreenOpened by remember {
                mutableStateOf(false)
            }

            navController = rememberNavController()
//            Navigation(navController = navController)

            MYJSweetFeetzTheme {
                if (!otherScreenOpened) {
                    Scaffold(
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
                        Navigation(this@MainActivity, navController = navController)
                        Box(
                            modifier = Modifier.padding(it),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(context: Context, navController: NavHostController) {
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
        composable("signup") {
            SignupScreen(context)
        }
        composable("login") {
            LoginScreen(context)
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
            if (firebaseAuth.currentUser == null) {
                Button(
                    onClick = { navController.navigate("signup") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3b70e0)
                    ),
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Text(text = "Sign Up")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3b70e0)
                    ),
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Text(text = "Log in")
                }
            } else {
                Text(
                    text = "Welcome, ${firebaseAuth.currentUser!!.email}!",
                    color = Color(0xFF000000),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(context: Context) {
    var currentEmail by remember {
        mutableStateOf("")
    }
    var psw by remember {
        mutableStateOf("")
    }
    var confirmPsw by remember {
        mutableStateOf("")
    }

    fun signUp() {
        if (currentEmail.isNotEmpty() && psw.isNotEmpty()) {
            if (currentEmail.contains("@") && currentEmail.contains(".")) {
                if (psw == confirmPsw) {
                    val signupinprocess = ProgressDialog(context)
                    signupinprocess.setMessage("Signing up...")
                    signupinprocess.setCancelable(false)
                    signupinprocess.show()
                    firebaseAuth.createUserWithEmailAndPassword(currentEmail, psw).addOnCompleteListener {
                        signupinprocess.cancel()
                        if (it.isSuccessful) {
                            navController.navigate("home")
                        } else {
                            Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Either email or password or both are empty", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = currentEmail,
            label = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                currentEmail = it
            },
            modifier = Modifier.fillMaxWidth(0.85f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = psw,
            label = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                psw = it
            },
            modifier = Modifier.fillMaxWidth(0.85f),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = confirmPsw,
            label = {
                Text(text = "Confirm Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                confirmPsw = it
            },
            modifier = Modifier.fillMaxWidth(0.85f),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { signUp() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3b70e0)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Sign Up")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(context: Context) {
    var currentEmail by remember {
        mutableStateOf("")
    }
    var psw by remember {
        mutableStateOf("")
    }

    fun login() {
        if (currentEmail.isNotEmpty() && psw.isNotEmpty()) {
            if (currentEmail.contains("@") && currentEmail.contains(".")) {
                val logininprocess = ProgressDialog(context)
                logininprocess.setMessage("Logging in...")
                logininprocess.setCancelable(false)
                logininprocess.show()
                firebaseAuth.signInWithEmailAndPassword(currentEmail, psw).addOnCompleteListener {
                    if (it.isSuccessful) {
                        navController.navigate("home")
                    } else {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    logininprocess.cancel()
                }
            } else {
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Either email or password or both are empty", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = currentEmail,
            label = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                currentEmail = it
            },
            modifier = Modifier.fillMaxWidth(0.85f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = psw,
            label = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                psw = it
            },
            modifier = Modifier.fillMaxWidth(0.85f),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { login() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3b70e0)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Login")
        }
    }
}