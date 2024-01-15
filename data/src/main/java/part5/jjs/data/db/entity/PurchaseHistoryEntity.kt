package part5.jjs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import part5.jjs.data.db.converter.PurchaseHistoryConverter
import part5.jjs.domain.model.BasketProduct
import part5.jjs.domain.model.PurchaseHistory
import java.time.ZonedDateTime

@Entity(tableName = "history")
@TypeConverters(PurchaseHistoryConverter::class)
data class PurchaseHistoryEntity(
    val basketList: List<BasketProduct>,
    val purchaseAt: ZonedDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun PurchaseHistoryEntity.toDomainModel(): PurchaseHistory {
    return PurchaseHistory(
        basketList = basketList, purchaseAt = purchaseAt,
    )
}

fun PurchaseHistory.toEntity(): PurchaseHistoryEntity {
    return PurchaseHistoryEntity(basketList = basketList, purchaseAt = purchaseAt)
}