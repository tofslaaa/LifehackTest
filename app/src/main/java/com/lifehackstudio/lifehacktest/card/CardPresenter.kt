package com.lifehackstudio.lifehacktest.card

import android.content.Intent
import android.location.Location
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.lifehackstudio.lifehacktest.toPhoneNumber
import com.lifehackstudio.lifehacktest.web.TestApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CardPresenter(private val view: View) {

    private val testApiService = TestApiService.invoke()
    private val compositeDisposable = CompositeDisposable()

    private val location = Location("")

    fun onStop() {
        compositeDisposable.clear()
    }

    fun loadCard(id: Long) {
        testApiService.getCard(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.fromIterable(it) }
            .subscribe({ result ->
                location.apply {
                    latitude = result.lat
                    longitude = result.lon
                }

                view.updateName(result.name)
                view.updateImage(result.img)
                view.updateDescription(result.description)
                view.updatePhone(result.phone.toPhoneNumber())
                view.updateSite(result.www)
            }, { error ->
                error.printStackTrace()
                view.showErrorLoad()
            }).also { compositeDisposable.add(it) }
    }

    fun onLocationClicked() {
        if (location.latitude == 0.0 && location.longitude == 0.0){
            view.showErrorLocation()
        } else {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = "geo:${location.latitude},${location.longitude}".toUri()
            }
            view.showLocation(intent)
        }
    }

    interface View {
        fun updateName(name: String)
        fun updateImage(imageUrl: String)
        fun updateDescription(description: String)
        fun updatePhone(phone: String)
        fun updateSite(site: String)

        fun showLocation(intent: Intent)

        fun showErrorLoad()
        fun showErrorLocation()
    }
}