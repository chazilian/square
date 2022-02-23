package com.foobear.square.data.repo

import com.foobear.square.data.entity.responses.EmpTypeEnumEntity
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.util.Resource

class FakeEmployeeRepository(): EmployeeRepository {

    private var shouldReturnEmptyList = false

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

    fun setEmpList(eList: MutableList<Employee>){
        employeeList = eList
    }

    fun getEmpList(): MutableList<Employee> {
        return employeeList
    }

    fun setShouldReturnEmptyList(value: Boolean){
        shouldReturnEmptyList = value
    }

    override suspend fun getEmployeeList(): Resource<List<Employee>> {
        return if (!shouldReturnEmptyList){
            Resource.success(employeeList)
        }else {
            Resource.success(emptyList<Employee>())
        }
    }
}