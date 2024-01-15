package part5.jjs.shoppingmall.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.SearchFilter
import part5.jjs.domain.model.SearchKeyword
import part5.jjs.domain.usecase.SearchUseCase
import part5.jjs.shoppingmall.delegate.ProductDelegate
import part5.jjs.shoppingmall.model.ProductVM
import part5.jjs.shoppingmall.ui.ProductDetailNav
import part5.jjs.shoppingmall.utils.NavigationUtils
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase
): ViewModel(), ProductDelegate {

    private val searchManager = SearchManager()
    private val _searchResult = MutableStateFlow<List<ProductVM>>(listOf())
    val searchResult: StateFlow<List<ProductVM>> = _searchResult
    val searchKeywords = useCase.getSearchKeywords()
    val searchFilters = searchManager.filters

    fun search(keyword: String) {
        viewModelScope.launch {
            searchInternalNewSearchKeyword(keyword)
        }
    }

    fun updateFilter(filter: SearchFilter) {
        viewModelScope.launch {
            searchManager.updateFilter(filter)

            searchInternal()
        }
    }

    private suspend fun searchInternal() {
        useCase.search(searchManager.searchKeyword, searchManager.currentFilters()).collectLatest {
            _searchResult.emit(it.map(::convertToProductVM))
        }
    }

    private suspend fun searchInternalNewSearchKeyword(newSearchKeyword: String = "") {
        searchManager.clearFilter()

        useCase.search(SearchKeyword(newSearchKeyword), searchManager.currentFilters()).collectLatest {
            searchManager.initSearchManager(newSearchKeyword, it)

            _searchResult.emit(it.map(::convertToProductVM))
        }
    }

    private fun convertToProductVM(product: Product): ProductVM {
        return ProductVM(product, this)
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigate(navHostController, ProductDetailNav.navigateWithArg(product.productId))
    }

    override fun likeProduct(product: Product) {
        viewModelScope.launch {
            useCase.likeProduct(product)
        }
    }
}