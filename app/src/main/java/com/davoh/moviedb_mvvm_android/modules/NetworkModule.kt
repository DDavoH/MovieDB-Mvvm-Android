package com.davoh.moviedb_mvvm_android.modules

import com.davoh.moviedb_mvvm_android.datasources.remotedatasources.LocationRemoteDataSource
import com.davoh.moviedb_mvvm_android.framework.retrofit.APIServices
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    
    @Provides
    fun providesApiService( client : OkHttpClient): APIServices {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
            .create(APIServices::class.java)
    }
    
    @Provides
    fun provideUnsafeOkHttpClient(): OkHttpClient {
        return provideUnsafeOkHttpClientTimeOut(25000L)
    }
    
    private fun provideUnsafeOkHttpClientTimeOut(milliseconds: Long?): OkHttpClient {
        return try {
            val builder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder
                .callTimeout(milliseconds!!, TimeUnit.MILLISECONDS)
                .connectTimeout(milliseconds, TimeUnit.MILLISECONDS)
                .readTimeout(milliseconds, TimeUnit.MILLISECONDS)
                .writeTimeout(milliseconds, TimeUnit.MILLISECONDS)
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
    
    
    @Provides
    fun provideFirestoreDatabase(): FirebaseFirestore {
        return Firebase.firestore
    }
    @Provides
    fun providePositionRemoteDataSource(firestore: FirebaseFirestore):LocationRemoteDataSource{
        return LocationRemoteDataSource(firestore)
    }
    
    
    
}