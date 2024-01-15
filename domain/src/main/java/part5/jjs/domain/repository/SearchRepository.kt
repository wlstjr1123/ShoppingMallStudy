package part5.jjs.domain.repository

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.SearchFilter
import part5.jjs.domain.model.SearchKeyword

interface SearchRepository {
    suspend fun search(searchKeyword: SearchKeyword) : Flow<List<Product>>

    fun getSearchKeywords(): Flow<List<SearchKeyword>>

    suspend fun likeProduct(product: Product)
}