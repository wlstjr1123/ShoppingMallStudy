package part5.jjs.domain.repository

import part5.jjs.domain.model.TempModel

interface TempRepository {
    fun getTempModel(): TempModel
}