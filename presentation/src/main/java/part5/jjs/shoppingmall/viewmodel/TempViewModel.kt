package part5.jjs.shoppingmall.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import part5.jjs.domain.model.TempModel
import part5.jjs.domain.usecase.TempUseCase
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(private val useCase: TempUseCase): ViewModel() {

    fun getTempModel(): TempModel {
        return useCase.getTempModel()
    }
}