package part5.jjs.shoppingmall.utils

import androidx.navigation.NavHostController
import part5.jjs.shoppingmall.ui.BasketNav
import part5.jjs.shoppingmall.ui.CategoryNav
import part5.jjs.shoppingmall.ui.Destination
import part5.jjs.shoppingmall.ui.MainNav
import part5.jjs.shoppingmall.ui.NavigationRouteName
import part5.jjs.shoppingmall.ui.ProductDetailNav
import part5.jjs.shoppingmall.ui.PurchaseHistoryNav
import part5.jjs.shoppingmall.ui.SearchNav

object NavigationUtils {

    fun navigate(
        controller: NavHostController,
        routeName: String,
        args: Any? = null,
        backStackRouteName: String? = null,
        isLaunchSingleTop: Boolean = true,
        needToRestoreState: Boolean = true
    ) {

        controller.navigate(routeName) {
            if (backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true }
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }

    fun findDestination(route: String?): Destination {
        return when(route) {
            NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
            NavigationRouteName.MAIN_LIKE -> MainNav.Like
            NavigationRouteName.MAIN_HOME -> MainNav.Home
            NavigationRouteName.MAIN_CATEGORY -> MainNav.Category
            NavigationRouteName.SEARCH -> SearchNav
            NavigationRouteName.BASKET -> BasketNav
            NavigationRouteName.PURCHASE_HISTORY -> PurchaseHistoryNav

            ProductDetailNav.routeWithArgName() -> ProductDetailNav
            CategoryNav.routeWithArgName() -> CategoryNav
            else -> MainNav.Home
        }
    }
}