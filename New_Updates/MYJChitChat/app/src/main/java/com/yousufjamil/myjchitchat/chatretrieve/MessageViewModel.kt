package com.yousufjamil.myjchitchat.chatretrieve

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yousufjamil.myjchitchat.firebaseAuth

class MessageViewModel: ViewModel() {

    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        if (firebaseAuth.currentUser != null) {
            getMessages()
        }
    }

    private fun getMessages() {
        FirebaseDatabase.getInstance().getReference("Messages")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                val dataList = mutableListOf<Message?>()

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children) {
                        val messageSnap = DataSnap.getValue(Message::class.java)
                        dataList.add(messageSnap)
                    }
                    println("tests: $dataList")
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataState.Failure(error.message)
                }
            }
            )
    }
}