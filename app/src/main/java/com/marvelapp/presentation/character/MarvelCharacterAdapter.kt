package com.marvelapp.presentation.character

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.R
import com.marvelapp.databinding.ViewMarvelCharacterBinding
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.utils.loadImage
import kotlinx.coroutines.withContext

class MarvelCharacterAdapter(
    private var list: List<CharacterItem> = listOf(),
    private val onClickItem: (Long) -> Unit
) : RecyclerView.Adapter<MarvelCharacterAdapter.MarvelCharacterViewHolder>() {

    fun updateMarvelItems(newItem: List<CharacterItem>) {
        list = newItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val itemBinding =
            ViewMarvelCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MarvelCharacterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }

    override fun getItemCount() = list.size

    inner class MarvelCharacterViewHolder(private val binding: ViewMarvelCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marvelCharacter: CharacterItem) = with(marvelCharacter) {
            binding.ivCharacter.loadImage(getImageUrl())
            binding.tvCharacterName.text = name
            itemView.setOnClickListener { onClickItem(id) }

        }
    }
}