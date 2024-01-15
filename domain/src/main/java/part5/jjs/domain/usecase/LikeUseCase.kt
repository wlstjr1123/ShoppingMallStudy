package part5.jjs.domain.usecase

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.LikeRepository
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository: LikeRepository
) {

    fun getLikeProducts(): Flow<List<Product>> {
        return repository.getLikeProducts()
    }
}