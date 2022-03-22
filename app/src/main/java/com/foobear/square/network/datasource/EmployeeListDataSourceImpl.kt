package com.foobear.square.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foobear.square.data.SquareApi
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.util.Resource
import java.lang.Exception
import java.time.LocalDateTime
import kotlin.random.Random

class EmployeeListDataSourceImpl(private val squareApi: SquareApi): EmployeeListDataSource {

    var _employees = MutableLiveData<List<Employee>>()
    override val employees: LiveData<List<Employee>>
        get() = _employees

    override suspend fun fetchEmployeeList(): Resource<List<Employee>> {
        return try {
            val result = squareApi.getEmployeeList()
            if(result.isSuccessful && result.body() != null && !isNullInList(result.body()!!.employees)){
                val ingestedAt = LocalDateTime.now().toString()
                val empList = result.body()?.employees
                empList!!.forEach { x -> x.IngestedAt = ingestedAt }
                _employees.postValue(empList!!)
                Resource.success(empList)
            } else {
                Log.e("ERROR", result.message())
                _employees.postValue(emptyList())
                Resource.error("Unsuccessful Response", null)
            }
        } catch (e: Exception){
            _employees.postValue(emptyList())
            Resource.error(e.message.toString(), null)
        }
    }

    private fun isNullInList(list: List<Employee>): Boolean{
        for(item in list){
            when {
                item.id == null -> {
                    return true
                }
                item.fullName == null -> {
                    return true
                }
                item.emailAddress == null -> {
                    return true
                }
                item.employeeType == null -> {
                    return true
                }
                item.team == null -> {
                    return true
                }
            }
        }
        return false
    }
}