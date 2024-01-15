package part5.jjs.shoppingmall.ui.purchase_history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import part5.jjs.domain.model.BasketProduct
import part5.jjs.shoppingmall.R
import part5.jjs.domain.model.PurchaseHistory
import part5.jjs.shoppingmall.ui.component.Price
import part5.jjs.shoppingmall.utils.NumberUtils
import part5.jjs.shoppingmall.viewmodel.purchase_history.PurchaseHistoryViewModel

@Composable
fun PurchaseHistoryScreen(
    viewModel: PurchaseHistoryViewModel = hiltViewModel()
) {
    val purchaseHistory by viewModel.purchaseHistory.collectAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        purchaseHistory.forEach { 
            PurchaseHistoryCard(it)
        }
    }
}

fun LazyListScope.PurchaseHistoryCard(purchaseHistory: PurchaseHistory) {
    item {
        Text(
            fontSize = 16.sp,
            text = "결제 시기 : ${purchaseHistory.purchaseAt}"
        )
    }
    items(purchaseHistory.basketList.size) { index ->
        val currentItem = purchaseHistory.basketList[index]

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                contentDescription = "purchaseItem",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp)
            )
            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f)
            ) {
                Text(
                    fontSize = 14.sp,
                    text = "${currentItem.product.shop.shopName} - ${currentItem.product.productName}",
                )
                Price(product = currentItem.product)
            }
            Text(
                text = "${currentItem.count} 개",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp, 0.dp, 0.dp, 0.dp)
            )
        }
    }
    item {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 20.dp),
            fontSize = 16.sp,
            text = "${getTotalPrice(purchaseHistory.basketList)} 결재완료"
        )
    }
}

private fun getTotalPrice(list: List<BasketProduct>): String {
    val totalPrice = list.sumOf { it.product.price.finalPrice * it.count }
    return NumberUtils.numberFormatPrice(totalPrice)
}