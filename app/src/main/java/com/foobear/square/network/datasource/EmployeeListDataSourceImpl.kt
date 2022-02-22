package com.foobear.square.network.datasource

import android.util.Log
import com.foobear.square.data.SquareApi
import com.foobear.square.data.entity.Employee
import com.foobear.square.util.Resource
import java.lang.Exception
import kotlin.random.Random

class EmployeeListDataSourceImpl(private val squareApi: SquareApi): EmployeeListDataSource {

    override suspend fun fetchEmployeeList(): Resource<List<Employee>> {
        return try {
            //2 out of 3 calls will be a rainy day scenario
            val result = when(Random.nextInt(3)) {
                1 -> squareApi.getMalformedEmployeeList()
                2 -> squareApi.getEmptyEmployeeList()
                else -> squareApi.getEmployeeList()
            }
            if(result.isSuccessful && result.body() != null){
                if(!isNullInList(result.body()!!.employees)) {
                    Resource.success(result.body()?.employees)
                } else {
                    Resource.success(emptyList())
                }
            } else {
                Log.e("ERROR", result.message())
                Resource.error("Unsuccessful Response", null)
            }
        } catch (e: Exception){
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