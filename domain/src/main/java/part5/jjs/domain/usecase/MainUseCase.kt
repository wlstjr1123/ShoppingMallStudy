package part5.jjs.domain.usecase

import kotlinx.coroutines.flow.Flow
import part5.jjs.domain.model.BaseModel
import part5.jjs.domain.model.Product
import part5.jjs.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun getModelList(): Flow<List<BaseModel>> {
        return mainRepository.getModelList()
    }

    suspend fun likeProduct(product: Product) {
        mainRepository.likeProduct(product)
    }
}