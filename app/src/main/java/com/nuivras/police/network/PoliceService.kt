package com.nuivras.police

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://data.police.uk/api/"

interface PoliceService {

    @GET("crimes-street/all-crime")
    suspend fun getAllStreetLevelCrimes(
        @Query("lat") latitude: String,
        @Query("lng") longitude: String): List<StreetLevelCrime>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

object PoliceServiceAPI {
    val retrofitService : PoliceService by lazy { retrofit.create(PoliceService::class.java) }
}