package part5.jjs.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import part5.jjs.domain.model.AccountInfo
import part5.jjs.domain.repository.AccountRepository
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccountInfo(): StateFlow<AccountInfo?> {
        return accountRepository.getAccountInfo()
    }

    suspend fun signIn(accountInfo: AccountInfo) {
        accountRepository.signIn(accountInfo)
    }

    suspend fun signOut() {
        accountRepository.signOut()
    }
}