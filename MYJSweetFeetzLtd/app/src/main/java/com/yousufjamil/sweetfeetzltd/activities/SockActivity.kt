package com.yousufjamil.sweetfeetzltd.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yousufjamil.sweetfeetzltd.EXTRA_PLAYER
import com.yousufjamil.sweetfeetzltd.Player
import com.yousufjamil.sweetfeetzltd.R
import kotlinx.android.synthetic.main.activity_sock.*

class SockActivity : AppCompatActivity() {

    var sockStyles = Player("Normal", "Colourful")

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_PLAYER, sockStyles)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sock)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            sockStyles = savedInstanceState.getParcelable(EXTRA_PLAYER)!!
        }
    }

    fun backHome (view: View) {
        MaterialAlertDialogBuilder(this)
                .setTitle("Warning!")
                .setMessage("Are you sure you want to continue to home? Your selected data might be deleted.")
                .setPositiveButton("Yes") { dialog, which ->
                    val intenthome = Intent(this, MainActivity::class.java)
                    startActivity(intenthome)
                }
                .setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this, "Operation cancelled succesfully", Toast.LENGTH_SHORT).show()
                }
                .show()
    }

    fun normalBtnClicked(view: View) {
        if (sockStyles.type != "Striped") {
            sockStyles.type = "Striped"
        } else {
            sockStyles.type = "Normal"
        }
    }

    fun black_white_clicked(view: View) {
        if (sockStyles.colour != "Black or White") {
            sockStyles.colour = "Black or White"
        } else {
            sockStyles.colour = "Colourful"
        }
    }

    fun sockGenerate(view: View) {
     // val sockGenerateNowIntent = Intent(this, GeneraterActivity::class.java)
     // sockGenerateNowIntent.putExtra(EXTRA_PLAYER, sockStyles)
     // startActivity(sockGenerateNowIntent)
        if (sockStyles.type == "Striped" && sockStyles.colour == "Black or White") {
            sockImage.setImageResource(R.drawable.black_white_tripe_socks)
        } else if (sockStyles.type != "Striped" && sockStyles.colour != "Black or White") {
            sockImage.setImageResource(R.drawable.colourful_normal_socks)
        } else if (sockStyles.type == "Striped" && sockStyles.colour != "Black or White") {
            sockImage.setImageResource(R.drawable.colour_stripe_socks)
        }else if (sockStyles.type != "Striped" && sockStyles.colour == "Black or White") {
            MaterialAlertDialogBuilder(this)
                .setTitle("Black or White Colour")
                .setMessage("Please select a colour:")
                .setPositiveButton("Black") { dialog, which ->
                    sockImage.setImageResource(R.drawable.black_socks)
                }
                .setNegativeButton("White") { dialog, which ->
                    sockImage.setImageResource(R.drawable.white_socks)
                }
                .show()
        }
    }

}