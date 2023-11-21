package cl.rogarciap.marketapp.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.rogarciap.marketapp.entities.CategoryEntity

@Database(
    entities = [
        CategoryEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDAO(): ICategoryDAO
}