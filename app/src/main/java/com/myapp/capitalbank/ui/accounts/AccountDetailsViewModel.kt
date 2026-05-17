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

    private val accountId: String = checkNotNull(savedStateHandle["accountId"])

    private val _uiState = MutableStateFlow(AccountDetailsUiState())
    val uiState: StateFlow<AccountDetailsUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        repository.getTransactions(accountId).onEach { transactions ->
            _uiState.update { it.copy(transactions = transactions, isLoading = false) }
        }.launchIn(viewModelScope)
    }
}
