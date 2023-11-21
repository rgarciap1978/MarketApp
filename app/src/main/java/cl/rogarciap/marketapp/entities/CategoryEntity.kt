package cl.rogarciap.marketapp.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity (
    @PrimaryKey(autoGenerate = false)
    @NonNull
    val uuid: String,

    val name: String,

    val image: String,

    val status: Boolean
)