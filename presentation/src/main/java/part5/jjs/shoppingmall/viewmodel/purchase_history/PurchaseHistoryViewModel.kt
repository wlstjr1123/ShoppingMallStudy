package part5.jjs.shoppingmall.viewmodel.purchase_history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import part5.jjs.domain.usecase.PurchaseHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class PurchaseHistoryViewModel @Inject constructor(
    private val useCase: PurchaseHistoryUseCase
): ViewModel() {

    val purchaseHistory = useCase.getPurchaseHistory()
}