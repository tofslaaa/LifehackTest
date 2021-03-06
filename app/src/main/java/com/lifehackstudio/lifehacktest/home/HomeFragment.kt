package com.lifehackstudio.lifehacktest.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
            home_progress_bar.visibility = View.GONE
            recycler_company.visibility = View.VISIBLE
            home_error.visibility = View.GONE
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
            findNavController().navigate(
                R.id.action_homeFragment_to_cardFragment,
                bundleOf("ID" to id)
            )
        }
    }

    private val viewPresenter = object : HomePresenter.View {
        override fun updateRecycler(cards: List<Cards>) {
            adapter.updateItems(cards)
            swipe_refresh.isRefreshing = false
            home_progress_bar.visibility = View.GONE
            recycler_company.visibility = View.VISIBLE
        }

        override fun showError() {
            swipe_refresh.isRefreshing = false
            home_progress_bar.visibility = View.GONE
            home_error.visibility = View.VISIBLE
        }
    }

}