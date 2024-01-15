package part5.jjs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import part5.jjs.data.db.dao.LikeDao
import part5.jjs.data.db.entity.toDomainModel
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeDao: LikeDao
): LikeRepository {
    override fun getLikeProducts(): Flow<List<Product>> {
        return likeDao.getAll().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }
}