package com.foobear.square.data.repo

import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.util.Resource

interface EmployeeRepository {
    suspend fun getEmployeeList(): Resource<List<Employee>>
}