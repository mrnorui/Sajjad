package academy.nouri.mvp.data.database

import academy.nouri.mvp.utils.MOVIES_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MOVIES_TABLE)
data class MoviesEntity(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
)