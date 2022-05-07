package academy.nouri.mvp.data.database

import academy.nouri.mvp.utils.MOVIES_TABLE
import androidx.room.*

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(entity: MoviesEntity)

    @Delete
    fun deleteMovie(entity: MoviesEntity)

    @Query("SELECT * FROM $MOVIES_TABLE ORDER BY id DESC")
    fun getAllMovies(): MutableList<MoviesEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM $MOVIES_TABLE WHERE id = :movieId)")
    fun existsMovie(movieId: Int): Boolean
}