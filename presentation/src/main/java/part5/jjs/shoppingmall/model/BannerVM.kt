package part5.jjs.shoppingmall.model

import part5.jjs.domain.model.Banner
import part5.jjs.shoppingmall.delegate.BannerDelegate

class BannerVM(model: Banner, bannerDelegate: BannerDelegate)
    : PresentationVM<Banner>(model), BannerDelegate by bannerDelegate {
}