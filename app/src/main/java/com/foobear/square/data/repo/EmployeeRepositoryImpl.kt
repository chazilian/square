package com.foobear.square.data.repo

import com.foobear.square.data.entity.Employee
import com.foobear.square.network.datasource.EmployeeListDataSource
import com.foobear.square.util.Resource

class EmployeeRepositoryImpl(private val employeeListDataSource: EmployeeListDataSource): EmployeeRepository {

    override suspend fun getEmployeeList(): Resource<List<Employee>> {
        return employeeListDataSource.fetchEmployeeList()
    }

}