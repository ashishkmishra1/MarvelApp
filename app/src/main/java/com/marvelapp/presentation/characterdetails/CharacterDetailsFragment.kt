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
import com.marvelapp.utils.extension.showAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModel()
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val args: CharacterDetailsFragmentArgs by navArgs()

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
        binding.characterDetailsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.error.observe(viewLifecycleOwner) {
            requireContext().showAlertDialog(
                getString(R.string.title_error_message),
                false
            ) { _, _ ->
                findNavController().navigateUp()
            }
        }
    }


}