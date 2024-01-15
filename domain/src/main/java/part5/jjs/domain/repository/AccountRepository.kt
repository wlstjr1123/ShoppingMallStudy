package part5.jjs.domain.repository

import kotlinx.coroutines.flow.StateFlow
import part5.jjs.domain.model.AccountInfo

interface AccountRepository {
    fun getAccountInfo(): StateFlow<AccountInfo?>

    suspend fun signIn(accountInfo: AccountInfo)

    suspend fun signOut()
}