package part5.jjs.shoppingmall.model

import androidx.navigation.NavHostController
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.Ranking
import part5.jjs.shoppingmall.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate):PresentationVM<Ranking>(model) {

    fun openRankingProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendRankingLog()
    }

    fun likeProduct(product: Product) {
        productDelegate.likeProduct(product = product)
    }

    fun sendRankingLog() {

    }
}