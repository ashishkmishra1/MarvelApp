package com.marvelapp.data.datasource

class Source(private val remoteDataSource: RemoteDataSource){
    suspend fun fetchMarvelCharacter(limit:Int,offset:Int) = remoteDataSource.fetchMarvelCharacter(limit,offset)
    suspend fun fetchCharacterDetails(id:Long)= remoteDataSource.fetchCharacterDetails(id)
}