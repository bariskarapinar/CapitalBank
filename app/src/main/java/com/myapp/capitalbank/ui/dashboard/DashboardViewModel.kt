package com.myapp.capitalbank.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.capitalbank.data.model.Account
import com.myapp.capitalbank.data.model.User
import com.myapp.capitalbank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val user: User? = null,
    val accounts: List<Account> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: BankRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.refreshData()
            
            repository.getUser().collect { user ->
                _uiState.update { it.copy(user = user) }
                if (user != null) {
                    repository.getAccounts(user.id).collect { accounts ->
                        _uiState.update { it.copy(accounts = accounts, isLoading = false) }
                    }
                }
            }
        }
    }
}
