package com.marvelapp.domain.usecases

import com.marvelapp.domain.repository.MarvelCharacterRepository

class GetMarvelCharacterUseCase(private val marvelCharacterRepository: MarvelCharacterRepository) {
    suspend operator fun invoke(limit: Int, offset: Int) =
        marvelCharacterRepository.getMarvelCharacters(limit, offset)
}