package com.foobear.square.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.foobear.square.data.entity.responses.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployees(employee: List<Employee>)

    @Query("SELECT * FROM employee")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Query("SELECT * FROM employee WHERE id = :id")
    fun getAllEmployees(id: String): LiveData<Employee>

    @Query("DELETE FROM employee")
    suspend fun deleteAllEmployees()

    @Query("SELECT IngestedAt FROM employee LIMIT 1")
    fun getEmployeeIngestedAt(): String
}