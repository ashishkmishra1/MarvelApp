package com.marvelapp.domain.data
import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapperImpl
import com.marvelapp.data.remote.MarvelApi
import com.marvelapp.data.repository.MarvelCharacterRepositoryImpl
import com.marvelapp.domain.repository.MarvelCharacterRepository
import com.marvelapp.domain.testdata.TestData.MARVEL_CHARACTER_RESPONSE_LIST
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MarvelCharacterRepositoryTest {

    private val marvelAPiService: MarvelApi = mockk()
    private val mapperImpl = MarvelCharacterItemResponseMapperImpl()
    private lateinit var characterRepository: MarvelCharacterRepository

    @Before
    fun setUpTest() {
        characterRepository = initialiseRepository()

    }

    private fun initialiseRepository() = MarvelCharacterRepositoryImpl(
        marvelAPiService,
        mapperImpl,
    )

    @Test
    fun `Given filters When fetch characters Then verify first character list`() {
        runBlocking {
            //Given
            val id: Long = 1011334
            coEvery {
                marvelAPiService.getMarvelCharacters(
                    20,
                    0
                )
            } returns MARVEL_CHARACTER_RESPONSE_LIST

            //When
            val response = characterRepository.getMarvelCharacters(20, 0)

            //Then
            val expected = response.result.first() ==
                    mapperImpl.toMarvelCharacterItem(MARVEL_CHARACTER_RESPONSE_LIST.data.results.first()) &&
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
            runCatching { characterRepository.getMarvelCharacters(20, 0) }
                .onFailure { error ->
                    assert(error.message == exception.message)
                }
        }
    }

    @Test
    fun `Given character id When fetch character details Then verify character`() = runBlocking {
        //Given
        val id: Long = 1011334
        coEvery { marvelAPiService.getMarvelCharacterDetails(id) } returns MARVEL_CHARACTER_RESPONSE_LIST

        //When
        val response = characterRepository.getMarvelCharacterDetails(id)

        //Then
        val expected = response?.name == MARVEL_CHARACTER_RESPONSE_LIST.data.results.first().name
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
                characterRepository.getMarvelCharacterDetails(id)
            }.onFailure {
                assert(it.message == exception.message)
            }
        }
    }


}