package part5.jjs.shoppingmall.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import part5.jjs.data.repository.AccountRepositoryImpl
import part5.jjs.data.repository.BasketRepositoryImpl
import part5.jjs.data.repository.CategoryRepositoryImpl
import part5.jjs.data.repository.LikeRepositoryImpl
import part5.jjs.data.repository.MainRepositoryImpl
import part5.jjs.data.repository.ProductDetailRepositoryImpl
import part5.jjs.data.repository.PurchaseHistoryRepositoryImpl
import part5.jjs.data.repository.SearchRepositoryImpl
import part5.jjs.data.repository.TempRepositoryImpl
import part5.jjs.domain.repository.AccountRepository
import part5.jjs.domain.repository.BasketRepository
import part5.jjs.domain.repository.CategoryRepository
import part5.jjs.domain.repository.LikeRepository
import part5.jjs.domain.repository.MainRepository
import part5.jjs.domain.repository.ProductDetailRepository
import part5.jjs.domain.repository.PurchaseHistoryRepository
import part5.jjs.domain.repository.SearchRepository
import part5.jjs.domain.repository.TempRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl): TempRepository

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    fun bindProductDetailRepository(productDetailRepositoryImpl: ProductDetailRepositoryImpl): ProductDetailRepository

    @Binds
    @Singleton
    fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    fun bingAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    fun bingLikeRepository(likeRepositoryImpl: LikeRepositoryImpl): LikeRepository

    @Binds
    @Singleton
    fun bingBasketRepository(basketRepositoryImpl: BasketRepositoryImpl): BasketRepository

    @Binds
    @Singleton
    fun bingPurchaseHistoryRepository(purchaseHistoryRepositoryImpl: PurchaseHistoryRepositoryImpl): PurchaseHistoryRepository
}