package part5.jjs.shoppingmall.model

import androidx.navigation.NavHostController
import part5.jjs.domain.model.Carousel
import part5.jjs.domain.model.Product
import part5.jjs.shoppingmall.delegate.ProductDelegate

class CarouselVM(model: Carousel,private val productDelegate: ProductDelegate): PresentationVM<Carousel>(model) {

    fun openCarouselProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendCarouselLog()
    }

    fun likeProduct(product: Product) {
        productDelegate.likeProduct(product = product)
    }

    fun sendCarouselLog() {

    }
}