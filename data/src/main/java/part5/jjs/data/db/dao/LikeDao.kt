package part5.jjs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import part5.jjs.data.db.entity.BasketProductEntity
import part5.jjs.data.db.entity.LikeProductEntity

@Dao
interface LikeDao {

    @Query("SELECT * FROM `like`")
    fun getAll(): Flow<List<LikeProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LikeProductEntity)

    @Query("DELETE FROM `like` WHERE productId=:id")
    suspend fun delete(id: String)

    @Query("DELETE FROM `like`")
    suspend fun deleteAll()
}