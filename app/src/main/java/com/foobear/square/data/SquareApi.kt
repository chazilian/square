package com.foobear.square.data

import com.foobear.square.data.entity.responses.Employees
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface SquareApi {

    @GET("employees.json")
    suspend fun getEmployeeList(

    ): Response<Employees>

    @GET("employees_malformed.json")
    suspend fun getMalformedEmployeeList(

    ): Response<Employees>

    @GET("employees_empty.json")
    suspend fun getEmptyEmployeeList(

    ): Response<Employees>

    companion object{
        operator  fun invoke(
        ): SquareApi{
            val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(SquareApi::class.java)
        }
    }
}