package com.marvelapp.presentation.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.databinding.FragmentMarvelCharacterBinding
import com.marvelapp.domain.model.CharacterItem
import org.koin.androidx.viewmodel.ext.android.viewModel

const val SHOW_LOADING = 1
const val SHOW_DATA = 0
const val SHOW_ERROR = 2

class MarvelCharacterFragment : Fragment() {

    private val marvelViewModel: MarvelCharacterViewModel by viewModel()
    private lateinit var binding: FragmentMarvelCharacterBinding

    private val adapter = MarvelCharacterAdapter(onClickItem = ::onClickMarvelCharacter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        marvelViewModel.fetchMarvelCharacter()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarvelCharacterBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setUpRecyclerView()
    }

    private fun setupViewModel() {
        marvelViewModel.loading.observe(viewLifecycleOwner) {
            toggleViewState(SHOW_LOADING)
        }
        marvelViewModel.emptyResult.observe(viewLifecycleOwner) {
            toggleViewState(SHOW_ERROR)
        }
        marvelViewModel.character.observe(viewLifecycleOwner) { onLoadCharacterList(it) }
        marvelViewModel.error.observe(viewLifecycleOwner) {
            toggleViewState(SHOW_ERROR)
            binding.srlMarvelCharacters.isRefreshing = false
        }

    }

    private fun setUpRecyclerView() {
        with(binding) {
            val layoutManager = GridLayoutManager(context, 2)
            rvCharacters.layoutManager = layoutManager
            rvCharacters.adapter = adapter
        }
        setUpScrollListener()


    }

    private fun toggleViewState(viewPosition: Int) {
        binding.vfMarvelCharacter.displayedChild = viewPosition
    }

    private fun onLoadCharacterList(list: List<CharacterItem>) {
        with(binding) {
            toggleViewState(SHOW_DATA)
            adapter.updateMarvelItems(list)
            srlMarvelCharacters.isRefreshing = false
        }
    }

    private fun setUpScrollListener() {
        val scrollListener: RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val layoutManager = recyclerView.layoutManager as GridLayoutManager
                        val lastItemListPosition =
                            layoutManager.findLastCompletelyVisibleItemPosition()
                        val lastItemPosition = recyclerView.adapter?.itemCount?.minus(1)

                        if (lastItemListPosition == lastItemPosition) {
                            marvelViewModel.fetchMarvelCharacterNextPage()
                        }
                    }
                }

            }
        binding.rvCharacters.removeOnScrollListener(scrollListener)
        binding.rvCharacters.addOnScrollListener(scrollListener)
    }

    private fun onClickMarvelCharacter(id: Long) {
        findNavController().navigate(
            MarvelCharacterFragmentDirections.actionCharacterToCharacterDetailsFragment(
                id
            )
        )
    }


}