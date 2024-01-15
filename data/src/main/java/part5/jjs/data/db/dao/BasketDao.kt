package part5.jjs.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import part5.jjs.data.db.entity.BasketProductEntity

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket")
    fun getAll(): Flow<List<BasketProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BasketProductEntity)

    @Query("SELECT * FROM basket WHERE productId=:id")
    suspend fun get(id: String): BasketProductEntity?

    @Query("DELETE FROM basket WHERE productId=:id")
    suspend fun delete(id: String)

    @Query("DELETE FROM basket")
    suspend fun deleteAll()
}