package part5.jjs.shoppingmall.di

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "76bbb89da3db1a41d720a4a62497ebd0")
        MobileAds.initialize(this)
    }
}