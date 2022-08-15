package com.yousufjamil.myjassistant.utils

import com.yousufjamil.myjassistant.utils.Constants.OPEN_GOOGLE
import com.yousufjamil.myjassistant.utils.Constants.OPEN_SEARCH
import java.lang.Exception

object BotResponse {

    fun basicResponses(_message: String) : String {
        var random = (0..2).random()
        val message = _message.toLowerCase()
        return when{
            //Hello!
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Assalamoalaikum!"
                    2 -> "Hi there!"
                    else -> "Unknown error!"
                }
            }
            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing great! Thanks for asking!"
                    1 -> "Feeling great! What about you?"
                    2 -> "I'm fine. What about you?"
                    else -> "Unknown error!"
                }
            }
            //Flip a coin!
            message.contains("flip") && message.contains("coin") -> {
                var r = (0..1).random()
                val result = if (r == 0) "head" else "tails"

                "I flipped a coin and it landed on $result."
            }
            //Solve an equation
            message.contains("solve") -> {
                val equation: String? = message.substringAfter("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    answer.toString()
                } catch (e: Exception) {
                    "Sorry, but I can't solve that!"
                }
            }
            //Get the current time
            message.contains("time") && message.contains("?") -> {
                Time.timeStamp()
            }
            //Open Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }
            //Search
            message.contains("search") -> {
                OPEN_SEARCH
            }
            //Salam response
            message.contains("salam") -> {
                "Walaikumassalam Warahmatullah-i-Wabarakatuh. How may be I of your help?"
            }
            //What's up?
            message.contains("what's up") -> {
                when (random) {
                    0 -> "I'm doing great! Thanks for asking!"
                    1 -> "Feeling great! What about you?"
                    2 -> "I'm fine. What about you?"
                    else -> "Unknown error!"
                }
            }
            //Bad language
            message.contains("idiot") -> {
                "Please do not use slang language."
            }
            //Commands
            message.contains("what can you do") -> {
                "I can do multiple things: \n" +
                        "- Respond to 'hello' or 'salam' greeting \n" +
                        "- Respond to 'what's up' or 'how are you?' \n" +
                        "- Flip a coin \n" +
                        "- Solve difficult mathematical sums for you \n" +
                        "- Tell you the time \n" +
                        "- Open google for you \n" +
                        "- Search google for you\n" +
                        "- Make tea for you 🙂"
            }
            //Tea
            message.contains("make") && message.contains("tea") -> {
                "Here's your tea: \n" +
                        "☕"
            }
            else ->{
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "IDK."
                    2 -> "I don't understand your request! Try asking me something else!"
                    else -> "Unknown error!"
                }
            }

        }
    }

}