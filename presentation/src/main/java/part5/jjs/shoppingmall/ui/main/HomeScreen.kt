package part5.jjs.shoppingmall.ui.main


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

import part5.jjs.domain.model.Banner
import part5.jjs.domain.model.BannerList
import part5.jjs.domain.model.Carousel
import part5.jjs.domain.model.ModelType
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.Ranking
import part5.jjs.shoppingmall.model.BannerListVM
import part5.jjs.shoppingmall.model.BannerVM
import part5.jjs.shoppingmall.model.CarouselVM
import part5.jjs.shoppingmall.model.ProductVM
import part5.jjs.shoppingmall.model.RankingVM
import part5.jjs.shoppingmall.ui.component.BannerCard
import part5.jjs.shoppingmall.ui.component.BannerListCard
import part5.jjs.shoppingmall.ui.component.CarouselCard
import part5.jjs.shoppingmall.ui.component.ProductCard
import part5.jjs.shoppingmall.ui.component.RankingCard
import part5.jjs.shoppingmall.viewmodel.MainViewModel

@Composable
fun MainHomeScreen(navController: NavHostController, viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    val testId = "ca-app-pub-3940256099942544/6300978111"
    val adid = "ca-app-pub-4857560197467057/3831639038"
    val adRequest = AdRequest.Builder().build()

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount),

    ) {
        items(
            modelList.size,
            span = {index ->
                val item = modelList[index]
                val spanCount = getSpanCountByType(item.model.type, columnCount)
                GridItemSpan(spanCount)
            }
        ) {
            when(val item = modelList[it]) {
                is BannerVM -> BannerCard(presentationVM = item)
                is ProductVM -> ProductCard(navController, presentationVM = item)
                is BannerListVM -> BannerListCard(presentationVM = item)
                is CarouselVM -> CarouselCard(navController, presentationVM = item)
                is RankingVM -> RankingCard(navController, presentationVM = item)
            }
        }
    }

    AndroidView(modifier = Modifier.fillMaxWidth().height(50.dp),
        factory = {
            AdView(it).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = testId
                loadAd(adRequest)
            }
        }, update =  {
            it.loadAd(adRequest)
        })
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST, ModelType.CAROUSEL, ModelType.RANKING -> defaultColumnCount
    }
}