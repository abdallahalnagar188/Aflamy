package com.example.aflamy.di

import com.example.aflamy.constance.API_Key
import com.example.aflamy.constance.BASE_URL
import com.example.data.remote.Api
import com.example.domain.state.LocalUtil
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideLocalUtil(): LocalUtil {
        return LocalUtil
    }

    @Provides
    @Singleton
    fun provideOkHttp(localUtil: LocalUtil): OkHttpClient {
        val headerInterceptor = Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val originalUrl = originalRequest.url

            // Get the current language from LocalUtil (default to "en")
            val language = localUtil.getLang() ?: "en"

            // Update the URL to add the "language" query parameter
            val updatedUrl = originalUrl.newBuilder()
                .addQueryParameter("language", language)
                .build()

            // Create a new request with the updated URL
            val requestWithHeaders: Request = originalRequest.newBuilder()
                .url(updatedUrl)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer $API_Key")
                .build()

            chain.proceed(requestWithHeaders)
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
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



