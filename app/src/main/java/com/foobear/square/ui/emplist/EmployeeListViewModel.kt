package com.foobear.square.ui.emplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.data.repo.EmployeeRepository
import com.foobear.square.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeListViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {

    private val isRefreshingData: MutableLiveData<Boolean> = MutableLiveData()
    private val isEmptyData: MutableLiveData<Boolean> = MutableLiveData()

    val employees = employeeRepository.getEmployeeListRoom()

    init {
        employeeList()
        isRefreshingData.value = false
        isEmptyData.value = false

    }

    fun getIsRefreshing(): LiveData<Boolean> {
        return isRefreshingData
    }

    fun getIsEmpty(): LiveData<Boolean> {
        return isEmptyData
    }

    private fun employeeList() {
        isRefreshingData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val list = employeeRepository.getEmployeeList()
            withContext(Dispatchers.Main) {
                isEmptyData.value = list.status == Status.ERROR
                isRefreshingData.value = false
            }
        }
    }

    fun refreshEmployeeList(){
        employeeList()
    }
}