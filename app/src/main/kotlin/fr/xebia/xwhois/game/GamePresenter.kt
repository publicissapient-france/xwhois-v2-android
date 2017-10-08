package fr.xebia.xwhois.game

import fr.xebia.xwhois.model.Person
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class GamePresenter(private val view: GameContract.View) : GameContract.Presenter {

    private lateinit var persons: List<Person>
    private lateinit var loop: Disposable

    private val secondsAtBegin = 20
    private var remainingTime = secondsAtBegin
    private var currentPerson = 0
    private var score = 0

    private val secondsGood = 5
    private val secondsWrong = -5

    override fun init(persons: List<Person>) {
        this.persons = persons

        view.setHighScore(0)
        view.setScore(score)
        view.setTime(secondsAtBegin, 100)
    }

    override fun start() {
        loop = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    remainingTime -= 1
                    view.setTime(remainingTime, remainingTime * 100 / secondsAtBegin)
                    if (remainingTime <= 0) {
                        loop.dispose()
                        Observable.just(true).delay(400, TimeUnit.MILLISECONDS)
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    end()
                                })
                    }
                })
    }

    override fun play() {
        bind(persons[currentPerson])
    }

    override fun onUserClick(text: CharSequence?) {
        if (persons[currentPerson].name == text.toString()) {
            good()
        } else {
            wrong()
        }
        next()
    }

    private fun next() {
        view.setScore(score)
        if (currentPerson >= persons.size) {
            end()
        } else {
            play()
        }
    }

    private fun end() {
        view.close()
    }

    private fun wrong() {
        score += secondsWrong
    }

    private fun good() {
        score += secondsGood
    }

    private fun bind(person: Person) {
        view.setPic(person.pic)
        view.setAnswers(getAnswers(person))
    }

    private fun getAnswers(person: Person): List<String> {
        val answers = mutableListOf(person.name, "Estelle Boyer", "Laetitia Janné", "Anne Beauchart")
        Collections.shuffle(answers)
        return answers
    }
}