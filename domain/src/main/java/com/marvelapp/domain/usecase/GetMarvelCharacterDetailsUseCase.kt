package com.marvelapp.domain.usecase

import com.marvelapp.domain.repository.MarvelCharacterRepository

class GetMarvelCharacterDetailsUseCase(private val marvelCharacterRepository: MarvelCharacterRepository) {
    suspend operator fun invoke(id: Long) =
        marvelCharacterRepository.getMarvelCharacterDetails(id)
}