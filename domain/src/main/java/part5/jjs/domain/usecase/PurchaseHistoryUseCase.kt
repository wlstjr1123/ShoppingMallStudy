package part5.jjs.domain.usecase

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.PurchaseHistory
import part5.jjs.domain.repository.PurchaseHistoryRepository
import javax.inject.Inject

class PurchaseHistoryUseCase @Inject constructor(
    private val repository: PurchaseHistoryRepository
) {
    fun getPurchaseHistory(): Flow<List<PurchaseHistory>> {
        return repository.getPurchaseHistory()
    }
}