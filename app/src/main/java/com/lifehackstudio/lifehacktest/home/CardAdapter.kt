package com.lifehackstudio.lifehacktest.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifehackstudio.lifehacktest.R
import com.lifehackstudio.lifehacktest.web.Cards

class CardAdapter(private val listener: Listener) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val cards = mutableListOf<Cards>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = cards[position].name
        Glide.with(holder.image)
            .load("https://megakohz.bget.ru/test_task/${cards[position].image}")
            .into(holder.image)

        holder.itemView.setOnClickListener {
            listener.onItemClicked(cards[position].id)
        }
    }

    fun updateItems(cards: List<Cards>) {
        this.cards.clear()
        this.cards.addAll(cards)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.item_name)
        val image: AppCompatImageView = itemView.findViewById(R.id.item_image)
    }

    interface Listener {
        fun onItemClicked(id: Long)
    }
}