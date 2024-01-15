package part5.jjs.shoppingmall.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import part5.jjs.domain.model.AccountInfo
import part5.jjs.domain.model.Banner
import part5.jjs.domain.model.BannerList
import part5.jjs.domain.model.BaseModel
import part5.jjs.domain.model.Carousel
import part5.jjs.domain.model.Category
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.Ranking
import part5.jjs.domain.usecase.AccountUseCase
import part5.jjs.domain.usecase.CategoryUseCase
import part5.jjs.domain.usecase.LikeUseCase
import part5.jjs.domain.usecase.MainUseCase
import part5.jjs.shoppingmall.delegate.BannerDelegate
import part5.jjs.shoppingmall.delegate.CategoryDelegate
import part5.jjs.shoppingmall.delegate.ProductDelegate
import part5.jjs.shoppingmall.model.BannerListVM
import part5.jjs.shoppingmall.model.BannerVM
import part5.jjs.shoppingmall.model.CarouselVM
import part5.jjs.shoppingmall.model.PresentationVM
import part5.jjs.shoppingmall.model.ProductVM
import part5.jjs.shoppingmall.model.RankingVM
import part5.jjs.shoppingmall.ui.BasketNav
import part5.jjs.shoppingmall.ui.CategoryNav
import part5.jjs.shoppingmall.ui.ProductDetailNav
import part5.jjs.shoppingmall.ui.PurchaseHistoryNav
import part5.jjs.shoppingmall.ui.SearchNav
import part5.jjs.shoppingmall.utils.NavigationUtils
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase,
    private val accountUseCase: AccountUseCase,
    likeUseCase: LikeUseCase
) : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate {

    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount: StateFlow<Int> = _columnCount

    val modelList = mainUseCase.getModelList().map(::convertToPresentationVM)
    val categories = categoryUseCase.getCategories()
    val likeProducts = likeUseCase.getLikeProducts().map(::convertToPresentationVM)
    val accountInfo = accountUseCase.getAccountInfo()

    fun openSearchForm(navHostController: NavHostController) {
        NavigationUtils.navigate(navHostController, SearchNav.route)
    }

    fun openBasket(navHostController: NavHostController) {
        NavigationUtils.navigate(navHostController, BasketNav.route)
    }

    fun openPurchaseHistory(navHostController: NavHostController) {
        NavigationUtils.navigate(navHostController, PurchaseHistoryNav.route)
    }

    fun signIn(accountInfo: AccountInfo) {
        viewModelScope.launch {
            accountUseCase.signIn(accountInfo)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            accountUseCase.signOut()
        }
    }

    fun updateCoulmnCount(count: Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    override fun openProduct(navHostController: NavHostController ,product: Product) {
        NavigationUtils.navigate(navHostController, ProductDetailNav.navigateWithArg(product.productId))
    }

    override fun likeProduct(product: Product) {
        viewModelScope.launch {
            mainUseCase.likeProduct(product)
        }
    }

    override fun openBanner(bannerId: String) {

    }

    override fun openCategory(navHostController: NavHostController, category: Category) {
        NavigationUtils.navigate(navHostController, CategoryNav.navigateWithArg(category))
    }

    private fun convertToPresentationVM(list: List<BaseModel>): List<PresentationVM<out BaseModel>> {
        return list.map { model ->
            when (model) {
                is Product -> ProductVM(model, this)
                is Ranking -> RankingVM(model, this)
                is Carousel -> CarouselVM(model, this)
                is Banner -> BannerVM(model, this)
                is BannerList -> BannerListVM(model, this)
            }
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }
}