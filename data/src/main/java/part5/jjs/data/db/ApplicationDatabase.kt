package part5.jjs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import part5.jjs.data.db.dao.BasketDao
import part5.jjs.data.db.dao.LikeDao
import part5.jjs.data.db.dao.PurchaseDao
import part5.jjs.data.db.dao.PurchaseHistoryDao
import part5.jjs.data.db.dao.SearchDao
import part5.jjs.data.db.entity.BasketProductEntity
import part5.jjs.data.db.entity.LikeProductEntity
import part5.jjs.data.db.entity.PurchaseHistoryEntity
import part5.jjs.data.db.entity.PurchaseProductEntity
import part5.jjs.data.db.entity.SearchKeywordEntity

@Database(
    entities = [
        PurchaseProductEntity::class,
        LikeProductEntity::class,
        BasketProductEntity::class,
        SearchKeywordEntity::class,
        PurchaseHistoryEntity::class,
    ],
    version = 3,
)
abstract class ApplicationDatabase: RoomDatabase() {
    companion object {
        const val DB_NAME = "applicationDatabase.db"
    }

    abstract fun purchaseDao(): PurchaseDao
    abstract fun likeDao(): LikeDao
    abstract fun basketDao(): BasketDao
    abstract fun searchDao(): SearchDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
}