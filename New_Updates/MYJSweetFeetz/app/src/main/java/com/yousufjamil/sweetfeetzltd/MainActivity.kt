package com.yousufjamil.sweetfeetzltd

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.yousufjamil.sweetfeetzltd.ui.theme.MYJSweetFeetzTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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
            HomeScreen(context)
        }
        composable("design") {
            DesignScreen()
        }
        composable("feedback") {
            FeedbackScreen(context)
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
        composable("forgotpsw") {
            ForgotPassword(context)
        }
        composable("resetemailsent") {
            ResetEmailSent()
        }
        composable("feedbacksent") {
            FeedbackSuccessScreen()
        }
        composable("sock") {
            SockScreen(context)
        }
        composable("final") {
            FinalSock(context)
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
fun Title(text: String) {
    Text(
        text = "$text",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(500),
        fontSize = 40.sp
    )
}

@Composable
fun HomeScreen(context: Context) {
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
            Title(text = "Sweet Feetz Ltd.")
            Spacer(modifier = Modifier.height(30.dp))
            if (firebaseAuth.currentUser == null) {
                var user by remember { mutableStateOf(Firebase.auth.currentUser) }
                val launcher = rememberFirebaseAuthLauncher(
                    onAuthComplete = { result ->
                        user = result.user
                    },
                    onAuthError = {
                        user = null
                    }
                )
                val token = stringResource(R.string.web_client_id)
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
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Or Sign up/Sign in with:")
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = {
                        val gso =
                            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(token)
                                .requestEmail()
                                .build()
                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        launcher.launch(googleSignInClient.signInIntent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g_logo),
                        contentDescription = "Google Logo",
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
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
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
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
        if (firebaseAuth.currentUser != null) {
            Button(
                onClick = { navController.navigate("sock") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3b70e0)
                ),
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                val sockIcon = Icons.Default.Face
                Icon(imageVector = sockIcon, contentDescription = "Create a sock")
                Text(text = "Create sock", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        } else {
            Text(text = "Please log in first")
        }
    }
}

@Composable
fun SockScreen(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val sockIcon = Icons.Default.Face
        var selectedTypePlain by remember { mutableStateOf(false) }
        var selectedTypeStriped by remember { mutableStateOf(false) }
        var selectedColourBlackWhite by remember { mutableStateOf(false) }
        var selectedColourColoured by remember { mutableStateOf(false) }

        Icon(imageVector = sockIcon, contentDescription = "Create a sock", modifier = Modifier.size(90.dp))
        Title(text = "Create a sock")

        Spacer(modifier = Modifier.height(20.dp))

        Column (
            modifier = Modifier
                .background(Color(0xFFFFFFFF))
                .padding(20.dp)
        ) {
            Text(text = "What kind of sock would you like?")
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedTypePlain, onClick = {
                    selectedTypePlain = true
                    selectedTypeStriped = false
                })
                Text(text = "Plain")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedTypeStriped, onClick = {
                    selectedTypePlain = false
                    selectedTypeStriped = true
                })
                Text(text = "Striped")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "What colour of sock would you like?")
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedColourBlackWhite, onClick = {
                    selectedColourBlackWhite = true
                    selectedColourColoured = false
                })
                Text(text = "Black & White")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedColourColoured, onClick = {
                    selectedColourBlackWhite = false
                    selectedColourColoured = true
                })
                Text(text = "Coloured")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate("final")
                }
            ) {
                Text(text = "Get sock")
            }
        }
    }
}

@Composable
fun FinalSock(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(context: Context) {
    var rating by remember {
        mutableStateOf("")
    }
    var feedback by remember {
        mutableStateOf("")
    }

    val database = FirebaseDatabase.getInstance().reference

    fun postFeedback() {
        try {
            if (rating.toInt() in 1..5) {
                if (feedback.trim().isNotEmpty()) {
                    var hashMapFeedback = HashMap<String, String>()
                    hashMapFeedback["email"] = "${firebaseAuth.currentUser!!.email}"
                    hashMapFeedback["rating"] = rating
                    hashMapFeedback["feedback"] = feedback

                    database.child("Feedback").push().setValue(hashMapFeedback)

                    navController.navigate("feedbacksent")
                } else {
                    Toast.makeText(context, "Invalid Feedback", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Invalid rating", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Throwable) {
            Toast.makeText(context, "Error: $e", Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (firebaseAuth.currentUser != null) {
            Title(text = "Rate Us")
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                value = rating,
                label = {
                    Text(text = "Rating")
                },
                onValueChange = {
                    rating = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    textColor = Color(0xFF000000)
                ),
                modifier = Modifier.fillMaxWidth(0.85f),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Please enter a rating between 1 and 5 inclusive.",
                color = Color(0xFF727272),
                modifier = Modifier.fillMaxWidth(0.8f),
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = feedback,
                label = {
                    Text(text = "Feedback")
                },
                onValueChange = {
                    feedback = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    textColor = Color(0xFF000000)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .defaultMinSize(minHeight = 150.dp),
                maxLines = 10
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Please enter a concise feedback, including expectations. Avoid lengthy feedbacks.",
                color = Color(0xFF727272),
                modifier = Modifier.fillMaxWidth(0.8f),
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { postFeedback() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3b70e0)
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Submit")
            }
        } else {
            Text(text = "Please log in first")
        }
    }
}

@Composable
fun FeedbackSuccessScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (firebaseAuth.currentUser != null) {
            val iconCheck = Icons.Default.Check

            Icon(
                imageVector = iconCheck,
                contentDescription = "Check icon",
                modifier = Modifier.size(50.dp),
                tint = Color(0xFF008703)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Feedback Submitted Successfully",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Thank your for your feedback!",
                textAlign = TextAlign.Center
            )
        } else {
            Text(text = "Please log in first")
        }
    }
}

@Composable
fun ProfileScreen() {
    fun logout() {
        firebaseAuth.signOut()
        navController.navigate("home")
    }

    if (firebaseAuth.currentUser != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFd2ddf4))
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Title(text = "Your Profile")
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Welcome, ${firebaseAuth.currentUser!!.email}!",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { logout() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3b70e0)
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Logout")
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFd2ddf4))
                .padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Please log in first")
        }
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
        Title(text = "Sign Up")
        Spacer(modifier = Modifier.height(40.dp))
        TextField(
            value = currentEmail,
            label = {
                Text(text = "Email")
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFFFFFFF),
                textColor = Color(0xFF000000)
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
                containerColor = Color(0xFFFFFFFF),
                textColor = Color(0xFF000000)
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
                containerColor = Color(0xFFFFFFFF),
                textColor = Color(0xFF000000)
            ),
            modifier = Modifier.fillMaxWidth(0.85f),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
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
        Title(text = "Login")
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
                containerColor = Color(0xFFFFFFFF),
                textColor = Color(0xFF000000)
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
                containerColor = Color(0xFFFFFFFF),
                textColor = Color(0xFF000000)
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
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
                modifier = Modifier
                    .clickable {
                        navController.navigate("forgotpsw")
                    }
            )
        }
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
                        navController.navigate("resetemailsent")
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
            .fillMaxSize()
            .background(Color(0xFFd2ddf4)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = "Forgot Password")
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
                containerColor = Color(0xFFFFFFFF),
                textColor = Color(0xFF000000)
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
fun ResetEmailSent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd2ddf4))
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