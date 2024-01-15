package part5.jjs.domain.repository

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.Product

interface LikeRepository {
    fun getLikeProducts(): Flow<List<Product>>
}