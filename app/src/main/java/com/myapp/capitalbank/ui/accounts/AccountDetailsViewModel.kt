package com.myapp.capitalbank.ui.accounts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.capitalbank.data.model.Transaction
import com.myapp.capitalbank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class AccountDetailsUiState(
    val transactions: List<Transaction> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class AccountDetailsViewModel @Inject constructor(
    private val repository: BankRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // The account ID is required to fetch specific transactions. 
    // We default to an empty string to avoid crashes during state restoration.
    private val accountId: String = savedStateHandle.get<String>("accountId") ?: ""

    private val _uiState = MutableStateFlow(AccountDetailsUiState())
    val uiState: StateFlow<AccountDetailsUiState> = _uiState.asStateFlow()

    init {
        if (accountId.isNotEmpty()) {
            _uiState.update { it.copy(isLoading = true) }
            repository.getTransactions(accountId).onEach { transactions ->
                _uiState.update { it.copy(transactions = transactions, isLoading = false) }
            }.launchIn(viewModelScope)
        }
    }
}
