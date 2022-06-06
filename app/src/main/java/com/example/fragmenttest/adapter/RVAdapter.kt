package com.example.fragmenttest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttest.Communicator
import com.example.fragmenttest.R
import com.example.fragmenttest.datamodel.Article
import com.squareup.picasso.Picasso

class RVAdapter (val context: Context, val articles: List<Article>, private val listener: Communicator) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.raw_vp_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article: Article = articles[position]

        holder.cardTitle.text = article.title

        if (article.author.toString() == "null") {
            holder.cardAuthor.text = ""
        } else {
            holder.cardAuthor.text = article.author.toString()
        }

        Picasso
            .get()
            .load(article.urlToImage.toString())
            .fit()
            .centerCrop()
            .into(holder.cardImage)
    }

    override fun getItemCount(): Int = articles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var cardImage: ImageView = itemView.findViewById(R.id.cardImage)
        var cardTitle: TextView = itemView.findViewById(R.id.cardTitle)
        var cardAuthor: TextView = itemView.findViewById(R.id.cardAuthor)

        init {
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val image = articles[adapterPosition].urlToImage.toString()
            val name =  articles[adapterPosition].title.toString()
            val url = articles[adapterPosition].url.toString()
            if (position != RecyclerView.NO_POSITION) {
                listener.passData(position, name, image, url)
            }
        }


    }

}