package com.yousufjamil.myjchitchat

import android.app.ProgressDialog
import android.content.Context
import android.graphics.fonts.FontStyle
import android.net.Uri
import android.os.Bundle
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.yousufjamil.myjchitchat.navandtopbar.AppTopBar
import com.yousufjamil.myjchitchat.navandtopbar.MenuItem
import com.yousufjamil.myjchitchat.ui.theme.MYJChitChatTheme
import kotlinx.coroutines.launch
import java.util.Random

lateinit var firebaseAuth: FirebaseAuth
lateinit var navigator: NavHostController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJChitChatTheme {
                navigator = rememberNavController()

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            Column {
                                DrawerHeader() {
                                    scope.launch { drawerState.close() }
                                }
                                DrawerBody(
                                    items = listOf(
                                        MenuItem("home", "Home", Icons.Default.Home, "Home icon")
                                    ),
                                    modifier = Modifier
                                        .padding(10.dp, 20.dp),
                                    onItemClick = { item ->
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Clicked ${item.title}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        scope.launch { drawerState.close() }
                                    }
                                )
                            }
                        }
                    },
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen
                ) {
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
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(65.dp))
                            Navigation(context = this@MainActivity, navController = navigator)
                        }
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
            HomeScreen(context = context)
        }
        composable("login") {
            LoginScreen(context = context)
        }
        composable("forgot-password") {
            ForgotPassword(context = context)
        }
        composable("reset-email-sent") {
            ResetEmailSent(context = context)
        }
        composable("sign-up") {
            SignupScreen(context = context)
        }
    }
}

@Composable
fun DrawerHeader(onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profiledefault),
                contentDescription = "Default profile logo",
                modifier = Modifier.padding(horizontal = 22.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        navigator.navigate("login")
                        onItemClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Text(text = "Login")
                }
                IconButton(onClick = {
                    onItemClick()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.addchannelbutton),
                        contentDescription = "Add channel",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFFFFFFFF)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    var selected by remember { mutableStateOf("Home") }
    Column(modifier = modifier) {
        items.forEach { item ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                },
                label = { Text(text = item.title) },
                selected = item.title == selected,
                onClick = {
                    onItemClick(item)
                    selected = item.title
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFD3E8FF)
                )
            )
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
                        navigator.navigate("home")
                    } else {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    logininprocess.cancel()
                }
            } else {
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                context,
                "Either email or password or both are empty",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        TextField(
            value = currentEmail,
            label = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                currentEmail = it
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD3E8FF)
            ),
            modifier = Modifier.fillMaxWidth(0.85f),
            singleLine = true
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
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD3E8FF)
            ),
            modifier = Modifier.fillMaxWidth(0.85f),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier.fillMaxWidth(0.8f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "Forgot Password?",
                textAlign = TextAlign.End,
                color = Color(0xFF3b70e0),
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp,
                modifier = Modifier
                    .clickable {
                        navigator.navigate("forgot-password")
                    }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { login() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Don't have an account?",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { navigator.navigate("sign-up") },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Get an account now!")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(context: Context) {
    var currentEmail by remember {
        mutableStateOf("")
    }

    fun forgotPassword() {
        if (currentEmail.isNotEmpty()) {
            if (currentEmail.contains("@") && currentEmail.contains(".")) {
                val resetEmailSend = ProgressDialog(context)
                resetEmailSend.setMessage("Sending Email...")
                resetEmailSend.setCancelable(false)
                resetEmailSend.show()
                firebaseAuth.sendPasswordResetEmail(currentEmail).addOnCompleteListener {
                    if (it.isSuccessful) {
                        navigator.navigate("reset-email-sent")
                    } else {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    resetEmailSend.cancel()
                }
            } else {
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.sendPasswordResetEmail(currentEmail)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Forgot Password", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = currentEmail,
            label = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                currentEmail = it
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD3E8FF)
            ),
            modifier = Modifier.fillMaxWidth(0.85f),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { forgotPassword() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3b70e0)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun ResetEmailSent(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val iconCheck = Icons.Default.Check

        Icon(
            imageVector = iconCheck,
            contentDescription = "Check icon",
            modifier = Modifier.size(50.dp),
            tint = Color(0xFF008703)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Password Reset Email has been sent successfully.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "In case you cannot find the password reset email, please check your Spam or Junk folder.",
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(context: Context) {
    var userName by remember {
        mutableStateOf("")
    }
    var currentEmail by remember {
        mutableStateOf("")
    }
    var psw by remember {
        mutableStateOf("")
    }
    var confirmPsw by remember {
        mutableStateOf("")
    }
    var avatar by remember {
        mutableStateOf("light1 0,0,0")
    }
    var color by remember {
        mutableStateOf("0,0,0")
    }
    var done by remember {
        mutableStateOf(false)
    }


    fun signUp() {
        if (currentEmail.isNotEmpty() && psw.isNotEmpty() && userName.isNotEmpty()) {
            if (currentEmail.contains("@") && currentEmail.contains(".")) {
                if (psw == confirmPsw) {
                    val signupinprocess = ProgressDialog(context)
                    signupinprocess.setMessage("Signing up...")
                    signupinprocess.setCancelable(false)
                    signupinprocess.show()
                    firebaseAuth.createUserWithEmailAndPassword(currentEmail, psw)
                        .addOnCompleteListener {
                            signupinprocess.cancel()
                            if (it.isSuccessful) {
                                val addUserNameAndPhoto = userProfileChangeRequest {
                                    displayName = userName
                                    photoUri = Uri.parse(avatar)
                                }
                                firebaseAuth.currentUser!!.updateProfile(addUserNameAndPhoto)

                                navigator.navigate("home")
                            } else {
                                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                context,
                "Either email or password or username or all are empty",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun generateAvatar() {
        val lightdark = Random().nextInt(2)
        avatar = if (lightdark == 0) " dark" else "light"
        val imagenum = Random().nextInt(28)
        avatar += imagenum.toString()
        val r = Random().nextInt(256)
        val g = Random().nextInt(256)
        val b = Random().nextInt(256)
        avatar += " $r,$g,$b"
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userName,
            label = {
                Text(text = "Username")
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD3E8FF)
            ),
            onValueChange = {
                userName = it
            },
            modifier = Modifier.fillMaxWidth(0.85f),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = currentEmail,
            label = {
                Text(text = "Email")
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD3E8FF)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                currentEmail = it
            },
            modifier = Modifier.fillMaxWidth(0.85f),
            singleLine = true
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
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD3E8FF)
            ),
            modifier = Modifier.fillMaxWidth(0.85f),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
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
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFD3E8FF)
            ),
            modifier = Modifier.fillMaxWidth(0.85f),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        IconButton(
            onClick = {
                generateAvatar()
                done = false
            },
            colors = IconButtonDefaults.iconButtonColors(
//                containerColor = Color()
            )
        ) {
            var id by remember {
                mutableIntStateOf(R.drawable.dark0)
            }
            if (!done) {
                try {
//                    println("tests: $avatar")
//                    if (avatar.substring(7,8) == " ") {
//                        var name = avatar.substring(0, 6)
//                        name = name.trim()
//                        println("tests: $name")
//
//                        avatar = avatar.removeRange(0, 7)
//                        color = avatar
//                        println("tests: $color")
//                    } else {
//                        var name = avatar.substring(0, 7)
//                        name = name.trim()
//                        println("tests: $name")
//
//                        avatar = avatar.removeRange(0, 7)
//                        color = avatar
//                        println("tests: $color")
//                    }
                    var name = avatar.substring(0, 6)
                    name = name.trim()
                    println("tests: $name")

                    avatar = avatar.removeRange(0, 7)
                    color = avatar
                    println("tests: $color")

                    id = context.resources.getIdentifier(
                        name,
                        "drawable",
                        context.packageName
                    )
                } catch (e: Throwable) {
                    Toast.makeText(context, e.stackTraceToString(), Toast.LENGTH_SHORT).show()
                }
                done = true
            }
            Icon(painter = painterResource(id = id), contentDescription = "Icon")
        }
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
fun HomeScreen(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Please Log In")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                var message by remember {
                    mutableStateOf("")
                }
                TextField(
                    value = message,
                    onValueChange = {
                        message = it
                    },
                    label = {
                        Text(text = "Message")
                    },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFD3E8FF)
                    )
                )
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send Message"
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}