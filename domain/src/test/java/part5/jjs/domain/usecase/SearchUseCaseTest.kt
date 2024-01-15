package part5.jjs.domain.usecase

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import part5.jjs.domain.model.Category
import part5.jjs.domain.model.Product
import part5.jjs.domain.model.SearchFilter
import part5.jjs.domain.model.SearchKeyword
import part5.jjs.domain.repository.SearchRepository
import com.google.common.truth.Truth.assertThat

@Suppress("NonAsciiCharacters")
@OptIn(ExperimentalCoroutinesApi::class)
class SearchUseCaseTest {

    private lateinit var useCase: SearchUseCase

    @Mock
    private lateinit var searchRepository: SearchRepository

    @Mock
    private lateinit var topProduct: Product
    @Mock
    private lateinit var dressProduct: Product
    @Mock
    private lateinit var pantsProduct: Product

    private lateinit var searchResponse: List<Product>

    private lateinit var autoCloseable: AutoCloseable

    private val searchKeyword = SearchKeyword("1")


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        autoCloseable = MockitoAnnotations.openMocks(this)

        Mockito.`when`(topProduct.category).thenReturn(Category.Top)
        Mockito.`when`(dressProduct.category).thenReturn(Category.Dress)
        Mockito.`when`(pantsProduct.category).thenReturn(Category.Pants)

        Mockito.`when`(topProduct.productName).thenReturn("상의1")
        Mockito.`when`(dressProduct.productName).thenReturn("드레스1")
        Mockito.`when`(pantsProduct.productName).thenReturn("바지1")

        searchResponse = listOf(topProduct, dressProduct, pantsProduct)

        useCase = SearchUseCase(searchRepository)
    }

    @After
    fun close() {
        Dispatchers.resetMain()
        autoCloseable.close()
    }

    @Test
    fun `검색 호출 테스트`() {
        useCase.getSearchKeywords()

        Mockito.verify(searchRepository).getSearchKeywords()
    }

    @Test
    fun `상의 필터 검색 테스트`() = runTest {
        Mockito.`when`(searchRepository.search(searchKeyword)).thenReturn( flow { emit(searchResponse) } )

        useCase.search(searchKeyword, listOf(SearchFilter.CategoryFilter(listOf(), Category.Top))).test {
            assertThat(awaitItem()).isEqualTo(listOf(topProduct))
            awaitComplete()
        }
    }

    @Test
    fun `드레스 필터 검색 테스트`() = runTest {
        Mockito.`when`(searchRepository.search(searchKeyword)).thenReturn( flow { emit(searchResponse) } )

        useCase.search(searchKeyword, listOf(SearchFilter.CategoryFilter(listOf(), Category.Top))).test {
            assertThat(awaitItem()).isEqualTo(listOf(dressProduct))
            awaitComplete()
        }
    }

    @Test
    fun `검색어 테스트`() = runTest {
        Mockito.`when`(searchRepository.search(SearchKeyword("상의"))).thenReturn( flow { emit(searchResponse) })

        useCase.search(SearchKeyword("상의"), listOf()).test {
            assertThat(awaitItem()).isEqualTo(listOf(topProduct))
            awaitComplete()
        }
    }

    @Test
    fun `검색어 필터 테스트`() = runTest {
        Mockito.`when`(searchRepository.search(SearchKeyword("바지"))).thenReturn( flow { emit(searchResponse) })

        useCase.search(SearchKeyword("바지"), listOf(SearchFilter.CategoryFilter(listOf(), Category.Pants))).test {
            assertThat(awaitItem()).isEqualTo(listOf(pantsProduct))
            awaitComplete()
        }
    }
}