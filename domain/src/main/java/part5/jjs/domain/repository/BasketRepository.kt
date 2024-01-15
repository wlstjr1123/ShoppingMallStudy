package part5.jjs.domain.repository

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.BasketProduct
import part5.jjs.domain.model.Product

interface BasketRepository {
    fun getBasketProducts(): Flow<List<BasketProduct>>

    suspend fun removeBasketProduct(product: Product)

    suspend fun checkoutBasket(products: List<BasketProduct>)
}