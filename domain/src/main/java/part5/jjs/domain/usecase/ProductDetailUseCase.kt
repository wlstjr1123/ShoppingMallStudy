package part5.jjs.domain.usecase

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.ProductDetailRepository
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
    private val repository: ProductDetailRepository
) {
    fun getProductDetail(productId: String) : Flow<Product> {
        return repository.getProProductDetail(productId = productId)
    }

    suspend fun addBasket(product: Product) {
        repository.addBasket(product)
    }
}