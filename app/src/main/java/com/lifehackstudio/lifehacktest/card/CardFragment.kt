package com.lifehackstudio.lifehacktest.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.lifehackstudio.lifehacktest.R
import kotlinx.android.synthetic.main.card_fragment.*
import kotlinx.android.synthetic.main.card_layout.*

class CardFragment : Fragment(R.layout.card_fragment) {
    private val presenter by lazy { CardPresenter(viewPresenter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { it.findNavController().popBackStack() }

        val id = arguments?.get("ID") as Long
        presenter.loadCard(id)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private val viewPresenter = object : CardPresenter.View {
        override fun updateName(name: String) {
            card_name.text = name

            card_progress_bar.visibility = View.GONE
            scroll_view.visibility = View.VISIBLE
        }

        override fun updateImage(imageUrl: String) {
            Glide.with(card_image.context)
                .load("http://megakohz.bget.ru/test_task/$imageUrl")
                .placeholder(R.drawable.placeholder_image)
                .into(card_image)
        }

        override fun updateDescription(description: String) {
            if (description.isEmpty()) card_description.visibility = View.GONE
            card_description.text = description
        }

        override fun updatePhone(phone: String) {
            if (phone.isEmpty()) phone_box.visibility = View.GONE
            card_phone.text = phone
        }

        override fun updateSite(site: String) {
            if (site.isEmpty()) site_box.visibility = View.GONE
            card_site.text = site
        }

        override fun showError() {
            card_progress_bar.visibility = View.GONE
            scroll_view.visibility = View.GONE
            card_error.visibility = View.VISIBLE
        }
    }
}