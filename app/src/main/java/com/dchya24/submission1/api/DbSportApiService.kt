package com.dchya24.submission1.api

import com.dchya24.submission1.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DbSportApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services: ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)
}