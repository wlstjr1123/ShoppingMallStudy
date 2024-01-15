package part5.jjs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import part5.jjs.data.datasource.ProductDataSource
import part5.jjs.data.db.dao.LikeDao
import part5.jjs.data.db.dao.SearchDao
import part5.jjs.data.db.entity.SearchKeywordEntity
import part5.jjs.data.db.entity.toDomain
import part5.jjs.data.db.entity.toLikeProductEntity
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.SearchFilter
import part5.jjs.domain.model.SearchKeyword
import part5.jjs.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val searchDAO: SearchDao,
    private val likeDao: LikeDao
): SearchRepository {
    override suspend fun search(searchKeyword: SearchKeyword): Flow<List<Product>> {
        searchDAO.insert(SearchKeywordEntity(searchKeyword.keyword))
        return dataSource.getProducts().combine(likeDao.getAll()) { products, likeList ->
            products.map { product -> updateLikeStatus(product, likeList.map {it. productId }) }
        }
    }



    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDAO.getAll().map { it.map { entity -> entity.toDomain() } }
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