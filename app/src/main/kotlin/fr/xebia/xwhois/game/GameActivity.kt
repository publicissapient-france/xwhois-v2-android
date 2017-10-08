package fr.xebia.xwhois.game

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fr.xebia.xwhois.R
import fr.xebia.xwhois.model.Person
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), GameContract.View {

    private val gamePresenter: GameContract.Presenter = GamePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initClickListener()
        gamePresenter.init(listOf(Person("Nadia Sidhoum", R.drawable.nsidhoum)))
        gamePresenter.start()
        gamePresenter.play()
    }

    private fun initClickListener() {
        gameName1.setOnClickListener { gamePresenter.onUserClick(gameName1.text) }
        gameName2.setOnClickListener { gamePresenter.onUserClick(gameName2.text) }
        gameName3.setOnClickListener { gamePresenter.onUserClick(gameName3.text) }
        gameName4.setOnClickListener { gamePresenter.onUserClick(gameName4.text) }
    }

    override fun setAnswers(answers: List<String>) {
        gameName1.text = answers[0]
        gameName2.text = answers[1]
        gameName3.text = answers[2]
        gameName4.text = answers[3]
    }

    override fun setPic(pic: Int) {
        gameImg.setImageResource(pic)
    }

    override fun setHighScore(score: Int) {
        gameHiScore.text = getString(R.string.high_score, score.toString())
    }

    override fun setScore(score: Int) {
        gameScore.text = score.toString()
    }

    override fun setTime(seconds: Int, progress: Int) {
        gameTime.text = "$seconds'"
        gameProgressTime.progress = progress
    }

    override fun close() {
        finish()
    }
}
