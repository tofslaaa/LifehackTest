package com.lifehackstudio.lifehacktest.card

import androidx.fragment.app.Fragment
import com.lifehackstudio.lifehacktest.R

class CardFragment : Fragment(R.layout.card_fragment) {
    private val presenter by lazy { CardPresenter(viewPresenter) }

    private val viewPresenter = object : CardPresenter.View {

    }
}