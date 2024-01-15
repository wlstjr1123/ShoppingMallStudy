package part5.jjs.domain.repository

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.BaseModel
import part5.jjs.domain.model.Product

interface MainRepository {
    fun getModelList(): Flow<List<BaseModel>>

    suspend fun likeProduct(product: Product)
}