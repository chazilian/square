package com.foobear.square.data.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foobear.square.TestCoroutineRule
import com.foobear.square.network.datasource.FakeEmployeeListDataSourceImpl
import com.foobear.square.util.Status
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmployeeRepositoryImplTest{
    private lateinit var repo: EmployeeRepositoryImpl

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup(){
        repo = EmployeeRepositoryImpl(FakeEmployeeListDataSourceImpl())
    }

    @Test
    fun `happy path - retrieve employee list`(){
        testCoroutineRule.runBlockingTest {
            //Act
            val result = repo.getEmployeeList()

            //Assert
            assertThat(result.data!!.size).isEqualTo(1)
            assertThat(result.status).isEqualTo(Status.SUCCESS)
        }
    }

    @Test
    fun `rainy path - retrieve error`(){
        testCoroutineRule.runBlockingTest {
            //Assign
            val dataSource = FakeEmployeeListDataSourceImpl()
            dataSource.setshouldReturnError(true)
            repo = EmployeeRepositoryImpl(dataSource)
            //Act
            val result = repo.getEmployeeList()

            //Assert
            assertThat(result.data).isEqualTo(null)
            assertThat(result.status).isEqualTo(Status.ERROR)
            assertThat(result.message).isEqualTo("Error")
        }
    }
}