package com.davoh.moviedb_mvvm_android.modules

import android.content.Context
import androidx.room.Room
import com.davoh.moviedb_mvvm_android.framework.room.movies.favoriteMovies.MovieFavoriteDao
import com.davoh.moviedb_mvvm_android.framework.room.movies.MoviesDatabase
import com.davoh.moviedb_mvvm_android.framework.room.movies.popularMovies.MoviePopularDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    
    @Singleton
    @Provides
    fun moviesDatabaseProvider(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_db"
        ).fallbackToDestructiveMigration().build()
    }
    
    @Provides
    fun provideMovieFavoriteDao(database: MoviesDatabase): MovieFavoriteDao {
        return database.movieFavoriteDao()
    }
    
   @Provides
   fun provideMoviePopularDao(database: MoviesDatabase): MoviePopularDao{
       return database.moviePopularDao()
   }
    
}