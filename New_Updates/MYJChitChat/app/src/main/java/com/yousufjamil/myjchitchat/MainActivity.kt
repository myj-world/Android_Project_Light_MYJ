package com.yousufjamil.myjchitchat

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.core.os.postDelayed
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.yousufjamil.myjchitchat.chatretrieve.DataState
import com.yousufjamil.myjchitchat.chatretrieve.Message
import com.yousufjamil.myjchitchat.chatretrieve.MessageViewModel
import com.yousufjamil.myjchitchat.navandtopbar.AppTopBar
import com.yousufjamil.myjchitchat.navandtopbar.MenuItem
import com.yousufjamil.myjchitchat.ui.theme.MYJChitChatTheme
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Random

lateinit var firebaseAuth: FirebaseAuth
lateinit var navigator: NavHostController
lateinit var retrievedresult: MutableList<Message?>

class MainActivity : ComponentActivity() {
    private val viewModel: MessageViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        setContent {
            MYJChitChatTheme {
                navigator = rememberNavController()
                retrievedresult = mutableListOf(Message("light1", "Loading...", "", ""))

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            Column {
                                DrawerHeader(context = this@MainActivity) {
                                    scope.launch { drawerState.close() }
                                }
                                DrawerBody(
                                    items = listOf(
                                        MenuItem("home", "Home")
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
            SetData(this@MainActivity, viewModel)
        }
    }
}

@Composable
fun SetData(context: Context, viewModel: MessageViewModel) {
    var recompose by remember {
        mutableStateOf(0)
    }

    Box(modifier = Modifier.padding(recompose.dp - recompose.dp)) {
        when (val result = viewModel.response.value) {
            is DataState.Loading -> {
                Handler().postDelayed(
                    {
                        if (recompose > 0) recompose-- else recompose++
                    },3000
                )
            }

            is DataState.Success -> {
                retrievedresult = result.data
            }

            is DataState.Failure -> {
                retrievedresult = mutableListOf(Message("light1", "Error", "", "Failed to load messages"))
            }

            is DataState.Empty -> {
                retrievedresult = mutableListOf(Message("light1", "Error", "", "No messages found"))
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
fun DrawerHeader(context: Context, onItemClick: () -> Unit) {
    var done by remember {
        mutableStateOf(false)
    }
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
            if (firebaseAuth.currentUser != null) {
                Row {
                    var photo = firebaseAuth.currentUser!!.photoUrl.toString()
                    var name by remember {
                        mutableStateOf("light0")
                    }
                    var color by remember {
                        mutableStateOf("#ffffff")
                    }

                    if (!done) {
                        try {
                            if (photo.contains("profiledefault")) {
                                name = photo.substring(0, 14)
                                name = name.trim()
                                println("tests: $name")

                                photo = photo.removeRange(0, 15)
                                color = photo
                                println("tests: $color")
                            } else {
                                if (photo.substring(6, 7) == " ") {
                                    name = photo.substring(0, 6)
                                    name = name.trim()
                                    println("tests: $name")

                                    photo = photo.removeRange(0, 7)
                                    color = photo
                                } else {
                                    name = photo.substring(0, 7)
                                    name = name.trim()
                                    println("tests: $name")

                                    photo = photo.removeRange(0, 8)
                                    color = photo
                                }
                            }
                        } catch (e: Throwable) {
                            Toast.makeText(context, e.stackTraceToString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                        done = true
                    }

                    val photoId = context.resources.getIdentifier(
                        name,
                        "drawable",
                        context.packageName
                    )
                    Image(
                        painter = painterResource(id = photoId),
                        contentDescription = "Default profile logo",
                        modifier = Modifier
                            .padding(start = 22.dp, end = 10.dp)
                            .background(Color(android.graphics.Color.parseColor(color)))
                            .width(70.dp)
                    )
                    Column {
                        Text(
                            text = firebaseAuth.currentUser!!.displayName.toString(),
                            color = Color(0xFFFFFFFF)
                        )
                        Text(
                            text = firebaseAuth.currentUser!!.email.toString(),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.profiledefault),
                    contentDescription = "Default profile logo",
                    modifier = Modifier.padding(horizontal = 22.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (firebaseAuth.currentUser != null) {
                            firebaseAuth.signOut()
                            navigator.navigate("home")
                        } else {
                            navigator.navigate("login")
                        }
                        onItemClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    if (firebaseAuth.currentUser != null) {
                        Text(text = "Logout")
                    } else {
                        Text(text = "Login")
                    }
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
    var uid by remember { mutableStateOf("Please Log In") }
    if (firebaseAuth.currentUser != null) {
        uid = firebaseAuth.uid.toString()
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            items.forEach { item ->
                NavigationDrawerItem(
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
        Text(text = "User id: $uid", modifier = Modifier.padding(10.dp))
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
    if (firebaseAuth.currentUser != null) {
        navigator.navigate("home")
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

    if (firebaseAuth.currentUser != null) {
        navigator.navigate("home")
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

        if (firebaseAuth.currentUser != null) {
            navigator.navigate("home")
        }

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
        mutableStateOf("profiledefault #FFFFFF")
    }
    var uploadAvatar by remember {
        mutableStateOf(avatar)
    }
    var color by remember {
        mutableStateOf("#FFFFFF")
    }
    var done by remember {
        mutableStateOf(false)
    }

    if (firebaseAuth.currentUser != null) {
        navigator.navigate("home")
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
                                    photoUri = Uri.parse(uploadAvatar)
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
        val randomColor = String.format("%06x", Random().nextInt(0x1000000))
        avatar += " #$randomColor"
        uploadAvatar = avatar
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
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Tap image to generate icon")
        Spacer(modifier = Modifier.height(10.dp))
        IconButton(
            onClick = {
                generateAvatar()
                done = false
            },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(android.graphics.Color.parseColor(color))
            ),
            modifier = Modifier
                .size(100.dp)
        ) {
            var id by remember {
                mutableIntStateOf(R.drawable.dark0)
            }
            if (!done) {
                try {
                    var name = ""
                    println("tests: $avatar")
                    if (avatar.contains("profiledefault")) {
                        name = avatar.substring(0, 14)
                        name = name.trim()
                        println("tests: $name")

                        avatar = avatar.removeRange(0, 15)
                        color = avatar
                        println("tests: $color")
                    } else {
                        if (avatar.substring(6, 7) == " ") {
                            name = avatar.substring(0, 6)
                            name = name.trim()
                            println("tests: $name")

                            avatar = avatar.removeRange(0, 7)
                            color = avatar
                            println("tests: $color")
                        } else {
                            name = avatar.substring(0, 7)
                            name = name.trim()
                            println("tests: $name")

                            avatar = avatar.removeRange(0, 8)
                            color = avatar
                            println("tests: $color")
                        }
                    }

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
            Icon(
                painter = painterResource(id = id),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(150.dp)
            )
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
    var message by remember {
        mutableStateOf("")
    }

    val database = FirebaseDatabase.getInstance().reference

    fun postMessage() {
        try {
            if (message.trim().isNotEmpty()) {
                val messageHashMap = HashMap<String, String>()
                messageHashMap["avatar"] = "${firebaseAuth.currentUser!!.photoUrl}"
                messageHashMap["username"] = "${firebaseAuth.currentUser!!.displayName}"
                messageHashMap["timestamp"] = "${Calendar.DATE} ${Calendar.getInstance().time}"
                messageHashMap["message"] = message

                database.child("Messages").push().setValue(messageHashMap)
                message = ""
            } else {
                Toast.makeText(context, "Invalid Feedback", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Throwable) {
            Toast.makeText(context, "Error: $e", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (firebaseAuth.currentUser == null) {
            Text(text = "Please Log In")
        } else {
            var recompose by remember {
                mutableStateOf(0)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize().padding(recompose.dp - recompose.dp)
            ) {
                @Composable
                fun message(
                    avatar: String,
                    name: String,
                    timestamp: String,
                    message: String
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profiledefault),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(10.dp)
                        )
                        Column {
                            Row(modifier = Modifier.padding(top = 10.dp)) {
                                Text(text = name)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = timestamp)
                            }
                            Text(text = message)
                        }
                    }
                }

                retrievedresult.forEach { message->
                    if (message != null) {
                        message(avatar = message.avatar, name = message.username, timestamp = message.timestamp, message = message.message)
                    }
                }

                Handler().postDelayed(
                    {
                        if (recompose > 0) recompose-- else recompose++
                    }, 3000
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = message,
                        onValueChange = {
                            message = it
                        },
                        label = {
                            Text(text = "Message")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFD3E8FF)
                        )
                    )
                    IconButton(onClick = { postMessage() }) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send Message",
                            tint = Color(0xFF97b6db)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}