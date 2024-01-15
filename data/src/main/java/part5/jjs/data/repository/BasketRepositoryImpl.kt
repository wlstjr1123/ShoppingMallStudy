package part5.jjs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import part5.jjs.data.db.dao.BasketDao
import part5.jjs.data.db.dao.PurchaseHistoryDao
import part5.jjs.data.db.entity.PurchaseHistoryEntity
import part5.jjs.data.db.entity.toDomainModel
import part5.jjs.domain.model.BasketProduct
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.BasketRepository
import java.time.ZonedDateTime
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val purchaseHistoryDao: PurchaseHistoryDao
): BasketRepository {
    override fun getBasketProducts(): Flow<List<BasketProduct>> {
        return basketDao.getAll().map { list ->
            list.map { BasketProduct(it.toDomainModel(), it.count) }
        }
    }

    override suspend fun removeBasketProduct(product: Product) {
        return basketDao.delete(product.productId)
    }

    override suspend fun checkoutBasket(products: List<BasketProduct>) {
        purchaseHistoryDao.insert(PurchaseHistoryEntity(products, ZonedDateTime.now()))
        basketDao.deleteAll()
    }
}