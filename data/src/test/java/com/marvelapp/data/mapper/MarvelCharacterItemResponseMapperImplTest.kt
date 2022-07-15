package com.marvelapp.data.mapper

import com.marvelapp.domain.testdata.TestData.MARVEL_CHARACTER_RESPONSE_LIST
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class MarvelCharacterItemResponseMapperImplTest {
    private val mapperImpl = MarvelCharacterItemResponseMapperImpl()

    @Test
    fun `verify mapper return result have correct data`() {
        runBlocking {
            assertEquals(
                MARVEL_CHARACTER_RESPONSE_LIST.data.results[0].name,
                mapperImpl.toMarvelCharacterItem(
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
                mapperImpl.toMarvelCharacterData(MARVEL_CHARACTER_RESPONSE_LIST.data).result.size
            )
        }
    }


}