package part5.jjs.shoppingmall.model

import part5.jjs.domain.model.Product
import part5.jjs.shoppingmall.delegate.ProductDelegate

class ProductVM(model: Product, productDelegate: ProductDelegate)
    : PresentationVM<Product>(model), ProductDelegate by productDelegate {
}