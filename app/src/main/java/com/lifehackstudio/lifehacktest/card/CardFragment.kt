package com.lifehackstudio.lifehacktest.card

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.lifehackstudio.lifehacktest.R
import kotlinx.android.synthetic.main.card_fragment.*
import kotlinx.android.synthetic.main.card_layout.*

class CardFragment : Fragment(R.layout.card_fragment) {
    private val presenter by lazy { CardPresenter(viewPresenter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { it.findNavController().popBackStack() }
        location_button.setOnClickListener { presenter.onLocationClicked() }

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
            val requestOption = RequestOptions().placeholder(R.drawable.placeholder_image)

            Glide.with(card_image.context)
                .load("http://megakohz.bget.ru/test_task/$imageUrl")
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(requestOption)
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

        override fun showLocation(intent: Intent) {
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                ContextCompat.startActivity(requireContext(), intent, null)
            } else {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    R.string.no_location_apps,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        override fun showErrorLoad() {
            card_progress_bar.visibility = View.GONE
            scroll_view.visibility = View.GONE
            card_error.visibility = View.VISIBLE
        }

        override fun showErrorLocation() {
            Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                R.string.error_location,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}