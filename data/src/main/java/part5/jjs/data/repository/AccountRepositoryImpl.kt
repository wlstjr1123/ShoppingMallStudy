package part5.jjs.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import part5.jjs.data.datasource.PreferenceDatasource
import part5.jjs.data.db.dao.BasketDao
import part5.jjs.data.db.dao.LikeDao
import part5.jjs.domain.model.AccountInfo
import part5.jjs.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val preferenceDatasource: PreferenceDatasource,
    private val basksetDao: BasketDao,
    private val likeDao: LikeDao,
) : AccountRepository {
    private val accountInfoFlow = MutableStateFlow(preferenceDatasource.getAccountInfo())

    override fun getAccountInfo(): StateFlow<AccountInfo?> {
        return accountInfoFlow
    }

    override suspend fun signIn(accountInfo: AccountInfo) {
        preferenceDatasource.putAccountInfo(accountInfo)
        accountInfoFlow.emit(accountInfo)
    }

    override suspend fun signOut() {
        preferenceDatasource.removeAccountInfo()
        accountInfoFlow.emit(null)
        basksetDao.deleteAll()
        likeDao.deleteAll()
    }
}