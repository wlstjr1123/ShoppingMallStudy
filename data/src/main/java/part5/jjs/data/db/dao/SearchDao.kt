package part5.jjs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import part5.jjs.data.db.entity.SearchKeywordEntity

@Dao
interface SearchDao {

    @Query("SELECT * FROM search")
    fun getAll(): Flow<List<SearchKeywordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SearchKeywordEntity)
}