package com.yousufjamil.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notkamui.keval.Keval
import com.yousufjamil.calculator.ui.theme.MYJCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MYJCalculatorTheme {
                var text by remember { mutableStateOf("") }

                Box (modifier = Modifier.background(Color(0xFF3a3a3c))) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(20.dp)
                            .background(Color(0xFF3a3a3c))
                    ) {
                        Text(
                            text = text,
                            modifier = Modifier
                                .fillMaxHeight(0.3f)
                                .fillMaxWidth(),
                            textAlign = TextAlign.End,
                            fontSize = if (text.length <= 6) {
                                100.sp
                            } else if (text.length <= 13) {
                                50.sp
                            } else {
                                25.sp
                            },
                            color = Color(0xFFFFFFFF)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxHeight(0.2f)
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(2.dp)
                        ) {
                            OperationButton(
                                operation = "AC", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text = "" }

                            OperationButton(
                                operation = "%", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) {
                                text = "($text)÷100"

                                try {
                                    text = text.replace("÷", "/")
                                    text = text.replace("×", "*")

                                    text = Keval.eval(text).toString()
                                } catch (e: Throwable) {
                                    Toast.makeText(this@MainActivity, e.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }

                            OperationButton(
                                operation = "DEL", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text = text.dropLast(1) }
                            OperatorButton(
                                operator = "÷", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "÷" }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxHeight(0.2f)
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(2.dp)
                        ) {
                            NumberButton(
                                number = "7", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "7" }
                            NumberButton(
                                number = "8", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "8" }
                            NumberButton(
                                number = "9", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "9" }
                            OperatorButton(
                                operator = "×", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "×" }

                        }

                        Row(
                            modifier = Modifier
                                .fillMaxHeight(0.2f)
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(2.dp)
                        ) {
                            NumberButton(
                                number = "4", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "4" }
                            NumberButton(
                                number = "5", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "5" }
                            NumberButton(
                                number = "6", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "6" }
                            OperatorButton(
                                operator = "+", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "+" }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxHeight(0.2f)
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(2.dp)
                        ) {
                            NumberButton(
                                number = "1", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "1" }
                            NumberButton(
                                number = "2", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "2" }
                            NumberButton(
                                number = "3", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "3" }
                            OperatorButton(
                                operator = "−", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "-" }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxHeight(0.2f)
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(2.dp)
                        ) {
                            NumberButton(
                                number = "0", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "0" }
                            NumberButton(
                                number = ".", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) { text += "." }
                            OperatorButton(
                                operator = "=", modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(3.dp)
                            ) {
                                try {
                                    text = text.replace("÷", "/")
                                    text = text.replace("×", "*")

                                    text = Keval.eval(text).toString()
                                } catch (e: Throwable) {
                                    Toast.makeText(this@MainActivity, e.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NumberButton(number: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = number, fontSize = 26.sp)
    }
}

@Composable
fun OperatorButton(operator: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors( containerColor = Color(0xFFff8d01) )
    ) {
        Text(text = operator, fontSize = 40.sp)
    }
}

@Composable
fun OperationButton(operation: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors( containerColor = Color(0xFF767676) )
    ) {
        Text(text = operation, fontSize = 20.sp)
    }
}