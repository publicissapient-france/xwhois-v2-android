package fr.xebia.xwhois.game

import fr.xebia.xwhois.model.Person

interface GameContract {
    interface View {
        fun setPic(pic: Int)
        fun setAnswers(answers: List<String>)
        fun setHighScore(score: Int)
        fun setScore(score: Int)
        fun setTime(seconds: Int, progress: Int)
        fun close()
    }

    interface Presenter {
        fun init(persons: List<Person>)
        fun start()
        fun play()
        fun onUserClick(text: CharSequence?)
    }
}