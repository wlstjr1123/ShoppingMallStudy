package part5.jjs.shoppingmall.model

import part5.jjs.domain.model.BannerList
import part5.jjs.shoppingmall.delegate.BannerDelegate

class BannerListVM(model: BannerList, private val bannerDelegate: BannerDelegate): PresentationVM<BannerList>(model) {
    fun openBannerList(bannerId: String) {
        bannerDelegate.openBanner(bannerId)
    }
}