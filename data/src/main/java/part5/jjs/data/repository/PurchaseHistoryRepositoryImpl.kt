package part5.jjs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import part5.jjs.data.db.dao.PurchaseHistoryDao
import part5.jjs.data.db.entity.toDomainModel
import part5.jjs.domain.model.PurchaseHistory
import part5.jjs.domain.repository.PurchaseHistoryRepository
import javax.inject.Inject

class PurchaseHistoryRepositoryImpl @Inject constructor(
    private val purchaseHistoryDao: PurchaseHistoryDao
): PurchaseHistoryRepository {
    override fun getPurchaseHistory(): Flow<List<PurchaseHistory>> {
        return purchaseHistoryDao.getAll().map { list ->
            list.map { it.toDomainModel() }
        }
    }
}