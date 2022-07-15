package com.marvelapp.data.repository

import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapperImpl
import com.marvelapp.data.remote.MarvelApi
import com.marvelapp.domain.testdata.TestData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MarvelCharacterRepositoryImplTest {
    private lateinit var characterRepositoryImpl: MarvelCharacterRepositoryImpl
    private val marvelAPiService: MarvelApi = mockk()
    private val mapperImpl = MarvelCharacterItemResponseMapperImpl()

    @Before
    fun setUpTest() {
        characterRepositoryImpl = initialiseRepository()

    }

    private fun initialiseRepository() = MarvelCharacterRepositoryImpl(
        marvelAPiService,
        mapperImpl,
    )

    @Test
    fun `Given filters When fetch characters Then verify first character list`() {
        runBlocking {
            //Given
            coEvery {
                marvelAPiService.getMarvelCharacters(
                    20,
                    0
                )
            } returns TestData.MARVEL_CHARACTER_RESPONSE_LIST

            //When
            val response = characterRepositoryImpl.getMarvelCharacters(20, 0)

            //Then
            val expected = response.result.first() ==
                    mapperImpl.toMarvelCharacterItem(TestData.MARVEL_CHARACTER_RESPONSE_LIST.data.results.first()) &&
                    response.result.size == 1
            assert(expected)
        }
    }

    @Test
    fun `Given filters When fetch characters with error Then verify error`() {
        runBlocking {
            //Given
            val exception = Exception("Marvel character not found")
            coEvery { marvelAPiService.getMarvelCharacters(20, 0) } throws exception

            // When & Then
            runCatching { characterRepositoryImpl.getMarvelCharacters(20, 0) }
                .onFailure { error ->
                    assert(error.message == exception.message)
                }
        }
    }

    @Test
    fun `Given character id When fetch character details Then verify character`() = runBlocking {
        //Given
        val id: Long = 1011334
        coEvery { marvelAPiService.getMarvelCharacterDetails(id) } returns TestData.MARVEL_CHARACTER_RESPONSE_LIST

        //When
        val response = characterRepositoryImpl.getMarvelCharacterDetails(id)

        //Then
        val expected =
            response?.name == TestData.MARVEL_CHARACTER_RESPONSE_LIST.data.results.first().name
        assert(expected)
    }


    @Test
    fun `Given character id When fetch character details with error Then verify error`() {
        runBlocking {
            //Given
            val exception = Exception("Marvel character details not found")
            val id: Long = 1011334
            coEvery { marvelAPiService.getMarvelCharacterDetails(id) } throws exception

            //When & Then
            runCatching {
                characterRepositoryImpl.getMarvelCharacterDetails(id)
            }.onFailure {
                assert(it.message == exception.message)
            }
        }
    }


}