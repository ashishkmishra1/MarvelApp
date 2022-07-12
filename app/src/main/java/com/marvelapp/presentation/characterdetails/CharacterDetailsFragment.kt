package com.marvelapp.presentation.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marvelapp.R
import com.marvelapp.databinding.FragmentCharacterDetailsBinding
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.utils.loadImage
import com.marvelapp.utils.showAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModel()
    private lateinit var binding: FragmentCharacterDetailsBinding
    val args: CharacterDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater)
        val id = args.id
        id.let { viewModel.fetchCharacterDetails(it) }
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
    }


    private fun setUpViewModel() {

        viewModel.data.observe(viewLifecycleOwner) {
            loadCharacterDetails(it)
        }


        viewModel.error.observe(viewLifecycleOwner) {
            requireContext().showAlertDialog(
                getString(R.string.title_error_message),
                false
            ) { _, _ ->
                findNavController().navigateUp()
            }
        }
    }

    private fun loadCharacterDetails(character: CharacterItem) {
        with(binding) {
            ivCharacter.loadImage(character.getImageUrl())
            tvCharacterDescription.text = character.description
            tvCharacterName.text = character.name
            tvComicsAvailable.text = String.format(
                resources.getString(R.string.comics_available),
                character.comics?.items?.size
            )
            tvSeriesAvailable.text = String.format(
                resources.getString(R.string.series_available),
                character.series?.items?.size
            )
            tvStoriesAvailable.text = String.format(
                resources.getString(R.string.stories_available),
                character.stories?.items?.size
            )
            tvEventAvailable.text = String.format(
                resources.getString(R.string.event_available),
                character.events?.items?.size
            )
        }
    }


}