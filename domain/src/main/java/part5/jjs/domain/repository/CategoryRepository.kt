package part5.jjs.domain.repository

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.Category
import part5.jjs.domain.model.Product

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProductsByCategory(category: Category) : Flow<List<Product>>

    suspend fun likeProduct(product: Product)
}