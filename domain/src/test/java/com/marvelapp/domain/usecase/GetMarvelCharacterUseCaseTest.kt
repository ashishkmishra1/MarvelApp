package com.marvelapp.domain.usecase

import com.marvelapp.data.datasource.Source
import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapperImpl
import com.marvelapp.data.repository.MarvelCharacterRepositoryImpl
import com.marvelapp.domain.testdata.TestData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMarvelCharacterUseCaseTest {
    private lateinit var characterRepositoryImpl: MarvelCharacterRepositoryImpl
    private val mapperImpl = MarvelCharacterItemResponseMapperImpl()
    private lateinit var getMarvelCharacterUseCase: GetMarvelCharacterUseCase
    private val source: Source = mockk()
    private fun initialiseRepository() = MarvelCharacterRepositoryImpl(
        source,
        mapperImpl,
    )

    @Before
    fun setUpTest() {
        characterRepositoryImpl = initialiseRepository()
        getMarvelCharacterUseCase = GetMarvelCharacterUseCase(characterRepositoryImpl)

    }

    @Test
    fun `invoke CharacterUsecase returns marvel character Then verify`() {
        runBlocking {
            //Given
            coEvery {
                source.fetchMarvelCharacter(
                    20,
                    0
                )
            } returns TestData.MARVEL_CHARACTER_RESPONSE_LIST

            //When
            val response = getMarvelCharacterUseCase.invoke(20, 0)

            //Then
            val expected = response.result.first() ==
                    mapperImpl.toMarvelCharacterModel(TestData.MARVEL_CHARACTER_RESPONSE_LIST.data.results.first()) &&
                    response.result.size == 1
            assert(expected)

        }
    }

    @Test
    fun `invoke CharacterUsecase fetch characters with error Then verify error`() {
        runBlocking {
            //Given
            val exception = Exception("Marvel character not found")
            coEvery { source.fetchMarvelCharacter(20, 0) } throws exception

            // When & Then
            runCatching { getMarvelCharacterUseCase.invoke(20, 0) }
                .onFailure { error ->
                    assert(error.message == exception.message)
                }
        }
    }
}