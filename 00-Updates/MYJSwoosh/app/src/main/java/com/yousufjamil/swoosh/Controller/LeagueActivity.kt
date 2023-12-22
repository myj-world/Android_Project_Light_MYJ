package com.yousufjamil.swoosh.Controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import com.yousufjamil.swoosh.Model.Player
import com.yousufjamil.swoosh.R
import com.yousufjamil.swoosh.Utilities.EXTRA_PLAYER

class LeagueActivity : BaseActivity() {

    var player = Player("", "")

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_PLAYER, player)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            player = savedInstanceState.getParcelable(EXTRA_PLAYER)!!
        }
    }

//    val womensLeagueBtn = findViewById<ToggleButton>(R.id.womensLeagueBtn)
//    val coedLeagueBtn = findViewById<ToggleButton>(R.id.coedLeagueBtn)
//    val mensLeagueBtn = findViewById<ToggleButton>(R.id.mensLeagueBtn)

    fun onMensClicked(view: View) {
        val womensLeagueBtn = findViewById<ToggleButton>(R.id.womensLeagueBtn)
        val coedLeagueBtn = findViewById<ToggleButton>(R.id.coedLeagueBtn)

        womensLeagueBtn.isChecked = false
        coedLeagueBtn.isChecked = false

        player.league = "mens"
    }

    fun onWomensClicked(view: View) {
        val coedLeagueBtn = findViewById<ToggleButton>(R.id.coedLeagueBtn)
        val mensLeagueBtn = findViewById<ToggleButton>(R.id.mensLeagueBtn)
        mensLeagueBtn.isChecked = false
        coedLeagueBtn.isChecked = false

        player.league = "womens"
    }

    fun onCoEdClicked(view: View) {
        val womensLeagueBtn = findViewById<ToggleButton>(R.id.womensLeagueBtn)
        val mensLeagueBtn = findViewById<ToggleButton>(R.id.mensLeagueBtn)
        mensLeagueBtn.isChecked = false
        womensLeagueBtn.isChecked = false

        player.league = "coed"
    }

    fun leagueNextClicked(view: View) {
        if (player.league != "") {
            val skillActivity = Intent(this, SkillActivity::class.java)
            skillActivity.putExtra(EXTRA_PLAYER, player)
            startActivity(skillActivity)
        } else{
            Toast.makeText(this, "Please select a league.", Toast.LENGTH_SHORT).show()
        }

    }
}