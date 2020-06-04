package com.lifehackstudio.lifehacktest.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lifehackstudio.lifehacktest.R
import com.lifehackstudio.lifehacktest.web.Cards
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val presenter by lazy { HomePresenter(viewPresenter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_company.apply {
            adapter = CardAdapter(listenerAdapter)
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
            TODO("Not yet implemented")
        }
    }

    private val viewPresenter = object : HomePresenter.View {
        override fun updateRecycler(cards: List<Cards>) {

        }

        override fun showError() {
            TODO("Not yet implemented")
        }
    }

}