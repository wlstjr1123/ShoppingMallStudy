package part5.jjs.domain.usecase

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.Category
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val repository: CategoryRepository) {

    fun getCategories(): Flow<List<Category>> {
        return repository.getCategories()
    }

    fun getProductsByCategory(category: Category) : Flow<List<Product>> {
        return repository.getProductsByCategory(category)
    }

    suspend fun likeProduct(product: Product) {
        repository.likeProduct(product)
    }
}