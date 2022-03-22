package com.foobear.square.data.repo

import androidx.lifecycle.LiveData
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.util.Resource

interface EmployeeRepository {
    suspend fun getEmployeeList(): Resource<List<Employee>>
    fun getEmployeeListRoom(): LiveData<List<Employee>>
    fun getSingleEmployeeRoom(id: String): LiveData<Employee>
}