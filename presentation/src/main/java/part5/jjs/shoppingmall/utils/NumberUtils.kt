package part5.jjs.shoppingmall.utils

import java.text.NumberFormat

object NumberUtils {
    fun numberFormatPrice(price: Int?): String {
        return NumberFormat.getNumberInstance().format(price ?: 0)
    }
}