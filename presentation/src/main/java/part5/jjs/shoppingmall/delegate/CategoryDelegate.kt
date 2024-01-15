package part5.jjs.shoppingmall.delegate

import androidx.navigation.NavHostController
import part5.jjs.domain.model.Category

interface CategoryDelegate {
    fun openCategory(navHostController: NavHostController, category: Category)
}