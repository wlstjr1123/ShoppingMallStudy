package part5.jjs.shoppingmall.delegate

import androidx.navigation.NavHostController
import part5.jjs.domain.model.Product

interface ProductDelegate {
    fun openProduct(navHostController: NavHostController, product: Product)

    fun likeProduct(product: Product)
}