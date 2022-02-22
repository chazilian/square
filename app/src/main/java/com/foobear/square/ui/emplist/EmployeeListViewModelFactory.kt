package com.foobear.square.ui.emplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foobear.square.data.repo.EmployeeRepository

class EmployeeListViewModelFactory(private val employeeRepository: EmployeeRepository):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun<T : ViewModel?> create(modelclass: Class<T>): T{
        return EmployeeListViewModel(employeeRepository) as T
    }
}