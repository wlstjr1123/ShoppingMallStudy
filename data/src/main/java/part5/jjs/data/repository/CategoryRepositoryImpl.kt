package part5.jjs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import part5.jjs.data.datasource.ProductDataSource
import part5.jjs.data.db.dao.LikeDao
import part5.jjs.data.db.entity.toLikeProductEntity
import part5.jjs.domain.model.Category
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val likeDao: LikeDao
): CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(listOf(Category.Top, Category.Bag, Category.Outerwear, Category.Dress, Category.FashionAccessories,
            Category.Pants, Category.Skirt, Category.Shoes
        ))
    }

    override fun getProductsByCategory(category: Category): Flow<List<Product>> {
        return dataSource.getHomeComponents().map { list ->
            list.filterIsInstance<Product>()
                .filter { product ->
                product.category.categoryId == category.categoryId
            }
        }.combine(likeDao.getAll()) { products, likeList ->
            products.map { product -> updateLikeStatus(product, likeList.map {it. productId }) }
        }
    }

    override suspend fun likeProduct(product: Product) {
        if (product.isLike) {
            likeDao.delete(product.productId)
        } else {
            likeDao.insert(product.toLikeProductEntity().copy(isLike = true))
        }
    }

    private fun updateLikeStatus(product: Product, likeProductIds: List<String>): Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}