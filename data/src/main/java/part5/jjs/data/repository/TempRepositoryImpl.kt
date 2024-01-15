package part5.jjs.data.repository

import part5.jjs.data.datasource.TempDataSource
import part5.jjs.domain.model.TempModel
import part5.jjs.domain.repository.TempRepository
import javax.inject.Inject

class TempRepositoryImpl @Inject constructor(private val dataSource: TempDataSource): TempRepository {
    override fun getTempModel(): TempModel {
        return dataSource.getTempModel()
    }
}