package com.foobear.square.network.datasource

import android.util.Log
import com.foobear.square.data.SquareApi
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.util.Resource
import java.lang.Exception
import kotlin.random.Random

class EmployeeListDataSourceImpl(private val squareApi: SquareApi): EmployeeListDataSource {

    override suspend fun fetchEmployeeList(): Resource<List<Employee>> {
        return try {
            val result = squareApi.getEmployeeList()
            if(result.isSuccessful && result.body() != null){
                Resource.success(result.body()?.employees)
            } else {
                Log.e("ERROR", result.message())
                Resource.error("Unsuccessful Response", null)
            }
        } catch (e: Exception){
            Resource.error(e.message.toString(), null)

        }
    }
}