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

class GetMarvelCharacterDetailsUseCaseTest {
    private lateinit var characterRepositoryImpl: MarvelCharacterRepositoryImpl
    private val mapperImpl = MarvelCharacterItemResponseMapperImpl()
    private lateinit var getMarvelCharacterDetailsUseCase: GetMarvelCharacterDetailsUseCase
    private val source: Source = mockk()

    private fun initialiseRepository() = MarvelCharacterRepositoryImpl(
        source,
        mapperImpl,
    )

    @Before
    fun setUpTest() {
        characterRepositoryImpl = initialiseRepository()
        getMarvelCharacterDetailsUseCase = GetMarvelCharacterDetailsUseCase(characterRepositoryImpl)

    }

    @Test
    fun `invoke characterdetailsusecase returns marvel character details Then verify`() {
        runBlocking {
            //Given
            val id: Long = 1011334

            coEvery {
                source.fetchCharacterDetails(id)
            } returns TestData.MARVEL_CHARACTER_RESPONSE_LIST

            //When
            val response = getMarvelCharacterDetailsUseCase.invoke(id)

            //Then
            val expected =
                response?.name == TestData.MARVEL_CHARACTER_RESPONSE_LIST.data.results.first().name
            assert(expected)

        }
    }

    @Test
    fun `invoke characterdetailsusecase fetch characters details with error Then verify error`() {
        runBlocking {
            //Given
            val id: Long = 1011334

            val exception = Exception("Marvel character not found")
            coEvery { source.fetchCharacterDetails(id) } throws exception

            //When & Then
            runCatching {
                getMarvelCharacterDetailsUseCase.invoke(id)
            }.onFailure {
                assert(it.message == exception.message)
            }
        }
    }
}