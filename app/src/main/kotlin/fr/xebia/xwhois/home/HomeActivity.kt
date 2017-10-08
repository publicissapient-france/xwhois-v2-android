package fr.xebia.xwhois.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fr.xebia.xwhois.R
import fr.xebia.xwhois.game.GameActivity
import kotlinx.android.synthetic.main.dashboard_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        homePlay.setOnClickListener { startActivity(Intent(this, GameActivity::class.java)) }
    }
}