package com.marvelapp.di

import com.marvelapp.BuildConfig
import com.marvelapp.data.remote.MarvelApi
import com.marvelapp.utils.HashUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { createService(get()) }
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single { createOkHttpClient() }

}
private fun getMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

fun createDefaultParameters(chain: Interceptor.Chain): Response {
    val timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    var request = chain.request()
    val builder = request.url.newBuilder()
    builder.addQueryParameter("apikey", BuildConfig.PUBLIC_API_KEY)
        .addQueryParameter(
            "hash",
            HashUtils.generateHash(
                BuildConfig.PRIVATE_API_KEY,
                BuildConfig.PUBLIC_API_KEY,
                timeStamp
            )
        )
        .addQueryParameter("ts", timeStamp.toString())
    request = request.newBuilder().url(builder.build()).build()
    return chain.proceed(request)
}

fun createService(retrofit: Retrofit): MarvelApi {
    return retrofit.create(MarvelApi::class.java)
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
        .build()
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor { chain -> createDefaultParameters(chain) }
        .addInterceptor(httpLoggingInterceptor).build()

}