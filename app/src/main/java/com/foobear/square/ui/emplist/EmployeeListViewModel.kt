package com.foobear.square.ui.emplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foobear.square.data.entity.Employee
import com.foobear.square.data.repo.EmployeeRepository
import com.foobear.square.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeListViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {

    private val empListLiveData: MutableLiveData<MutableList<Employee>> = MutableLiveData()
    private val isRefreshingData: MutableLiveData<Boolean> = MutableLiveData()
    private val isEmptyData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        employeeList()
        isRefreshingData.value = false
        isEmptyData.value = false

    }

    fun getEmpList(): LiveData<MutableList<Employee>> {
        return empListLiveData
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
            if (!list.data.isNullOrEmpty()) {
                    empListLiveData.value = list.data.toMutableList()
                    isEmptyData.value = false
                } else {
                    isEmptyData.value = true
                }
                isRefreshingData.value = false
            }
        }
    }

    fun refreshEmployeeList(){
        employeeList()
    }
}