package part5.jjs.domain.repository

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.PurchaseHistory

interface PurchaseHistoryRepository {
    fun getPurchaseHistory(): Flow<List<PurchaseHistory>>
}