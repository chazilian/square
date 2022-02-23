package com.foobear.square.network.datasource

import com.foobear.square.data.entity.responses.EmpTypeEnumEntity
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.util.Resource

class FakeEmployeeListDataSourceImpl: EmployeeListDataSource{

    private var shouldReturnError = false

    private var employeeList = mutableListOf<Employee>(
        Employee(id = "123",
            fullName = "Foo bar",
            phoneNumber = "305305305",
            emailAddress = "foo@bar.com",
            biography = "I am cool?",
            photoSmall = "https://squareup.com/us/en",
            photoLarge = "https://squareup.com/us/en",
            team = "Awesome team",
            employeeType = EmpTypeEnumEntity.FULL_TIME
        )
    )

    fun setshouldReturnError(value: Boolean){
        shouldReturnError = value
    }

    override suspend fun fetchEmployeeList(): Resource<List<Employee>> {
        return if(!shouldReturnError){
            Resource.success(employeeList)
        } else {
            Resource.error("Error", null)
        }
    }
}