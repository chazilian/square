package com.foobear.square.data.repo

import androidx.lifecycle.LiveData
import com.foobear.square.data.dao.EmployeeDao
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.network.datasource.EmployeeListDataSource
import com.foobear.square.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class EmployeeRepositoryImpl(
    private val employeeListDataSource: EmployeeListDataSource,
    private val employeeDao: EmployeeDao): EmployeeRepository {

    init {
        employeeListDataSource.apply {
            employees.observeForever {  emps ->
                GlobalScope.launch(Dispatchers.IO) {
                    if(emps.isNotEmpty()){
                        employeeDao.insertEmployees(emps)
                    } else {
                        employeeDao.deleteAllEmployees()
                    }
                }
            }
        }
    }


    override suspend fun getEmployeeList(): Resource<List<Employee>> {
        return employeeListDataSource.fetchEmployeeList()
    }

    override fun getEmployeeListRoom(): LiveData<List<Employee>> {
        return employeeDao.getAllEmployees()
    }

    override fun getSingleEmployeeRoom(id: String): LiveData<Employee> {
        return employeeDao.getAllEmployees(id)
    }

    private fun getEmployeeCache(): Boolean{
        val ingestedAt = employeeDao.getEmployeeIngestedAt()
        return !ingestedAt.isNullOrEmpty() && LocalDateTime.now() < LocalDateTime.parse(ingestedAt).plusMinutes(10)
    }
}