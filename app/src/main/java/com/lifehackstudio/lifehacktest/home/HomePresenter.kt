package com.lifehackstudio.lifehacktest.home

import com.lifehackstudio.lifehacktest.web.Cards
import com.lifehackstudio.lifehacktest.web.TestApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: View) {

    private val testApiService = TestApiService.create()
    private val compositeDisposable = CompositeDisposable()

    fun onStart() {
        loadCards()
    }

    fun loadCards(){
        testApiService.getCards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                view.updateRecycler(result)
            }, { error ->
                error.printStackTrace()
                view.showError()
            }).also { compositeDisposable.add(it) }
    }

    fun onStop() {
        compositeDisposable.clear()
    }

    interface View {
        fun updateRecycler(cards: List<Cards>)
        fun showError()
    }
}