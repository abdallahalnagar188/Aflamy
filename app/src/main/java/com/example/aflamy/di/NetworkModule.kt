package com.example.aflamy.di

import android.content.Context
import androidx.room.Room
import com.example.aflamy.constance.API_Key
import com.example.aflamy.constance.BASE_URL
import com.example.data.remote.Api
//import com.example.data.dao.MovieDao
//import com.example.data.remote.Api
//import com.example.data.repoImpl.MovieRepositoryImpl
//import com.example.data.room.MovieDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        // Create an interceptor to add headers to every request
        val headerInterceptor = Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val requestWithHeaders: Request = originalRequest.newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer $API_Key")
                .build()
            chain.proceed(requestWithHeaders)
        }

        // Optionally add logging interceptor for debugging purposes
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor) // Add header interceptor
            .addInterceptor(loggingInterceptor) // Add logging interceptor (optional)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
