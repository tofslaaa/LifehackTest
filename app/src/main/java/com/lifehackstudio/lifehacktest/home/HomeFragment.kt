package com.lifehackstudio.lifehacktest.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.lifehackstudio.lifehacktest.R
import com.lifehackstudio.lifehacktest.web.Cards
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val presenter by lazy { HomePresenter(viewPresenter) }
    private val adapter by lazy { CardAdapter(listenerAdapter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_company.adapter = this.adapter

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = true
            progress_bar.visibility = View.GONE
            recycler_company.visibility = View.VISIBLE
            error.visibility = View.GONE
            presenter.loadCards()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private val listenerAdapter = object : CardAdapter.Listener {
        override fun onItemClicked(id: Long) {
            Log.d("CardAdapter", "click on $id")
        }
    }

    private val viewPresenter = object : HomePresenter.View {
        override fun updateRecycler(cards: List<Cards>) {
            adapter.updateItems(cards)
            swipe_refresh.isRefreshing = false
            progress_bar.visibility = View.GONE
            recycler_company.visibility = View.VISIBLE
        }

        override fun showError() {
            swipe_refresh.isRefreshing = false
            progress_bar.visibility = View.GONE
            error.visibility = View.VISIBLE
        }
    }

}