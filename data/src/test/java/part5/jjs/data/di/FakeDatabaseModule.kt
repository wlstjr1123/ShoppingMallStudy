package part5.jjs.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import part5.jjs.data.db.ApplicationDatabase
import part5.jjs.data.db.dao.BasketDao
import part5.jjs.data.db.dao.LikeDao
import part5.jjs.data.db.dao.PurchaseHistoryDao
import part5.jjs.data.db.dao.SearchDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FakeDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ApplicationDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ApplicationDatabase::class.java,
            ApplicationDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchDao(database: ApplicationDatabase): SearchDao {
        return database.searchDao()
    }

    @Provides
    @Singleton
    fun provideLikeDao(database: ApplicationDatabase): LikeDao {
        return database.likeDao()
    }

    @Provides
    @Singleton
    fun provideBasketDao(database: ApplicationDatabase): BasketDao {
        return database.basketDao()
    }

    @Provides
    @Singleton
    fun providePurchaseHistoryDao(database: ApplicationDatabase): PurchaseHistoryDao {
        return database.purchaseHistoryDao()
    }
}