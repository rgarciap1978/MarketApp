package cl.rogarciap.marketapp

import android.content.Context
import androidx.room.Room
import cl.rogarciap.marketapp.daos.AppDatabase
import cl.rogarciap.marketapp.daos.ICategoryDAO
import cl.rogarciap.marketapp.repositories.CategoryRepository
import cl.rogarciap.marketapp.repositories.ProductRepository
import cl.rogarciap.marketapp.repositories.UserRepository
import cl.rogarciap.marketapp.repositories.interfaces.ICategoryRepository
import cl.rogarciap.marketapp.repositories.interfaces.IProductRepository
import cl.rogarciap.marketapp.repositories.interfaces.IUserRepository
import cl.rogarciap.marketapp.services.HeaderInterceptor
import cl.rogarciap.marketapp.services.IService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpInterceptor(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideController(httpClient: OkHttpClient): IService {
        return Retrofit
            .Builder()
            .baseUrl("http://35.169.242.154:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideIUserRepository(
        service: IService
    ): IUserRepository {
        return UserRepository(service)
    }

    @Provides
    @Singleton
    fun provideICategoryRepository(
        service: IService,
        dao: ICategoryDAO
    ): ICategoryRepository {
        return CategoryRepository(
            service,
            dao
        )
    }

    @Provides
    @Singleton
    fun provideIProductRepository(
        service: IService
    ): IProductRepository{
        return ProductRepository(service)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        name = "dbMarketApp"
    ).build()

    @Provides
    @Singleton
    fun provideDAO(
        appDatabase: AppDatabase
    ): ICategoryDAO = appDatabase.categoryDAO()
}