package com.marvelapp.presentation.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.BR
import com.marvelapp.databinding.ViewMarvelCharacterBinding
import com.marvelapp.domain.model.CharacterModel

class MarvelCharacterAdapter(
    private var list: List<CharacterModel> = listOf(),
    private val onClickItem: (Long) -> Unit
) : RecyclerView.Adapter<MarvelCharacterAdapter.MarvelCharacterViewHolder>() {

    fun updateMarvelItems(newItem: List<CharacterModel>) {
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
        fun bind(marvelCharacter: CharacterModel) = with(marvelCharacter) {
            binding.setVariable(BR.model, marvelCharacter)
            itemView.setOnClickListener { onClickItem(id) }
        }
    }
}