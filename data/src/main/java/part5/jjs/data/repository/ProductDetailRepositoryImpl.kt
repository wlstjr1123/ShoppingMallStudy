package part5.jjs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import part5.jjs.data.datasource.ProductDataSource
import part5.jjs.data.db.dao.BasketDao
import part5.jjs.data.db.entity.toBasketProductEntity
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.ProductDetailRepository
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val basketDao: BasketDao
): ProductDetailRepository {
    override fun getProProductDetail(productId: String): Flow<Product> {
        return dataSource.getHomeComponents().map { products ->
            products.filterIsInstance<Product>().first { it.productId == productId }
        }
    }

    override suspend fun addBasket(product: Product) {
        val alreadyExistsProduct = basketDao.get(product.productId)
        if (alreadyExistsProduct == null) {
            basketDao.insert(product.toBasketProductEntity())
        } else {
            basketDao.insert(alreadyExistsProduct.copy(count = alreadyExistsProduct.count + 1))
        }
    }
}