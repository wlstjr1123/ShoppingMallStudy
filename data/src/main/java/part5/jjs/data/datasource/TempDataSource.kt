package part5.jjs.data.datasource

import part5.jjs.domain.model.TempModel
import javax.inject.Inject

class TempDataSource @Inject constructor() {
    fun getTempModel(): TempModel {
        return TempModel("testModel")
    }
}