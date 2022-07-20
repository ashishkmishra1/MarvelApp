package com.marvelapp.data.mapper

import com.marvelapp.data.testdata.TestData.MARVEL_CHARACTER_RESPONSE_LIST
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class MarvelCharacterEntityMapperImplTest {
    private val mapperImpl = MarvelCharacterItemResponseMapperImpl()

    @Test
    fun `verify mapper return result have correct data`() {
        runBlocking {
            assertEquals(
                MARVEL_CHARACTER_RESPONSE_LIST.data.results[0].name,
                mapperImpl.toMarvelCharacterModel(
                    MARVEL_CHARACTER_RESPONSE_LIST.data.results[0]
                ).name
            )
        }
    }

    @Test
    fun `verify mapper return response item list size`() {
        runBlocking {
            assertEquals(
                MARVEL_CHARACTER_RESPONSE_LIST.data.results.size,
                mapperImpl.toMarvelCharacterDetailsModel(MARVEL_CHARACTER_RESPONSE_LIST.data).result.size
            )
        }
    }


}