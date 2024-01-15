package part5.jjs.data.repository

import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import part5.jjs.data.db.ApplicationDatabase
import part5.jjs.data.db.dao.BasketDao
import part5.jjs.data.db.dao.PurchaseHistoryDao
import part5.jjs.data.db.entity.BasketProductEntity
import part5.jjs.domain.model.BasketProduct
import part5.jjs.domain.model.Category
import part5.jjs.domain.model.Price
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.SalesStatus
import part5.jjs.domain.model.Shop
import part5.jjs.domain.repository.BasketRepository
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class BasketRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: ApplicationDatabase

    lateinit var basketDao: BasketDao
    lateinit var purchaseHistroyDao: PurchaseHistoryDao
    lateinit var basketRepository: BasketRepository

    private val price = Price(10000, 10000, SalesStatus.ON_SALE)
    private val shop = Shop("0", "0", "")
    private val category = Category.Top

    private val basketProductEntity =
        BasketProductEntity("", "", "", price, category, shop, false, false, false, 1)

    private val basketProduct = BasketProduct(
        Product("", "", "", price, category, shop, false, false, false), 1
    )

    private val product = Product("", "", "", price, category, shop, false, false, false)

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        hiltRule.inject()

        basketDao = database.basketDao()
        purchaseHistroyDao = database.purchaseHistoryDao()
        basketRepository = BasketRepositoryImpl(basketDao, purchaseHistroyDao)
    }

    @After
    fun close() {
        Dispatchers.resetMain()
    }

    @Test
    fun `결제 테스트`() = runTest {
        basketDao.insert(basketProductEntity)

        basketRepository.checkoutBasket(listOf(basketProduct))

        assertThat(purchaseHistroyDao.get("1")).isNotNull()
        assertThat(basketDao.get(basketProduct.product.productId)).isNull()
    }

    @Test
    fun `장바구니 제거 테스트`() = runTest {
        basketDao.insert(basketProductEntity)

        basketRepository.removeBasketProduct(product)

        assertThat(basketDao.get(product.productId)).isNull()
    }
}