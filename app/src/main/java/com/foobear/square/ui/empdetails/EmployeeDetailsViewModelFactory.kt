package com.foobear.square.ui.empdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foobear.square.data.repo.EmployeeRepository

class EmployeeDetailsViewModelFactory(private val employeeRepository: EmployeeRepository, private val id: String):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun<T : ViewModel?> create(modelclass: Class<T>): T{
        return EmployeeDetailsViewModel(employeeRepository, id) as T
    }
}