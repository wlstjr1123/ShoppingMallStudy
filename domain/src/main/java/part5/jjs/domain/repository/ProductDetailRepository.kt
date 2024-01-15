package part5.jjs.domain.repository

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.Product

interface ProductDetailRepository {
    fun getProProductDetail(productId: String): Flow<Product>

    suspend fun addBasket(product: Product)
}