package com.foobear.square.ui.empdetails

import androidx.lifecycle.ViewModel
import com.foobear.square.data.repo.EmployeeRepository

class EmployeeDetailsViewModel(employeeRepository: EmployeeRepository, id: String) : ViewModel() {

    val employee = employeeRepository.getSingleEmployeeRoom(id)
}