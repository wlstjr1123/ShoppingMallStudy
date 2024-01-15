package part5.jjs.domain.usecase

import part5.jjs.domain.model.TempModel
import part5.jjs.domain.repository.TempRepository
import javax.inject.Inject

class TempUseCase @Inject constructor(private val repository: TempRepository) {

    fun getTempModel(): TempModel {
        return repository.getTempModel()
    }
}