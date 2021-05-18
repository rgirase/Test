package com.sample.simpsonsviewer.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.data.model.RelatedTopic
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.ui.DetailsView
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(var context: Context, charArrayList: List<RelatedTopic?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var charactersList: List<RelatedTopic> = charArrayList as List<RelatedTopic>
    var characterFilterList: List<RelatedTopic> = charArrayList as List<RelatedTopic>
    var mContext = context

    init {
        characterFilterList = charactersList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return RecyclerViewViewHolder(rootView)
    }


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (characterFilterList.isNotEmpty()) {
            val character: RelatedTopic = characterFilterList[position]
            val viewHolder = holder as RecyclerViewViewHolder
            val charName = character.Text.split("-")
            viewHolder.txtView_char_name.text = charName[0]

            viewHolder.itemView.setOnClickListener {
                val intent = Intent(it.context, DetailsView::class.java)
                intent.putExtra("character_position", position)
                mContext.startActivity(intent)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    characterFilterList = charactersList
                } else {
                    val resultList = ArrayList<RelatedTopic>()
                    for (row in charactersList) {
                        if (row.Text.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    characterFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = characterFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                characterFilterList = results?.values as List<RelatedTopic>
                notifyDataSetChanged()
            }

        }
    }


    override fun getItemCount(): Int {
        return charactersList.size
    }

    internal inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var txtView_char_name: TextView = itemView.findViewById(R.id.txtView_char_name)

    }


}