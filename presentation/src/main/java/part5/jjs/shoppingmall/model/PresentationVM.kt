package part5.jjs.shoppingmall.model

import part5.jjs.domain.model.BaseModel

sealed class PresentationVM<T: BaseModel>(val model: T) {

    // some func
}