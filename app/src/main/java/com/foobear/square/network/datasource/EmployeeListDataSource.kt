package com.foobear.square.network.datasource

import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.util.Resource

interface EmployeeListDataSource {
    suspend fun fetchEmployeeList(): Resource<List<Employee>>
}