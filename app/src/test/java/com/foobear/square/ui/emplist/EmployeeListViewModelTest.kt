package com.foobear.square.ui.emplist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foobear.square.TestCoroutineRule
import com.foobear.square.data.repo.FakeEmployeeRepository
import com.foobear.square.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmployeeListViewModelTest{
    private lateinit var viewModel: EmployeeListViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup(){
        viewModel = EmployeeListViewModel(FakeEmployeeRepository())
    }

    @Test
    fun `happy path - retrieve employee list`(){
        testCoroutineRule.runBlockingTest {
            //Act
            viewModel.getEmpList()

            //Assert
            assertThat(viewModel.getEmpList().getOrAwaitValueTest().size).isEqualTo(1)
            assertThat(viewModel.getIsEmpty().getOrAwaitValueTest()).isEqualTo(false)
            assertThat(viewModel.getIsRefreshing().getOrAwaitValueTest()).isEqualTo(false)
        }
    }

    @Test
    fun `rainy path - retrieve empty list`(){
        testCoroutineRule.runBlockingTest {
            //Arrange
            val repo = FakeEmployeeRepository()
            repo.setShouldReturnEmptyList(true)
            viewModel = EmployeeListViewModel(repo)

            //Act & Assert
            assertThat(viewModel.getEmpList().getOrAwaitValueTest().size).isEqualTo(0)
            assertThat(viewModel.getIsEmpty().getOrAwaitValueTest()).isEqualTo(true)
            assertThat(viewModel.getIsRefreshing().getOrAwaitValueTest()).isEqualTo(false)
        }
    }

    @Test
    fun `rainy path - retrieve list with null phonenumber`(){
        testCoroutineRule.runBlockingTest {
            //Arrange
            val repo = FakeEmployeeRepository()
            val list = repo.getEmpList()
            list[0].phoneNumber = null.toString()
            repo.setEmpList(list)
            viewModel = EmployeeListViewModel(repo)

            //Act & Assert
            assertThat(viewModel.getEmpList().getOrAwaitValueTest().size).isEqualTo(1)
            assertThat(viewModel.getIsEmpty().getOrAwaitValueTest()).isEqualTo(false)
            assertThat(viewModel.getIsRefreshing().getOrAwaitValueTest()).isEqualTo(false)
        }
    }

    @Test
    fun `rainy path - retrieve list with null biography`(){
        testCoroutineRule.runBlockingTest {
            //Arrange
            val repo = FakeEmployeeRepository()
            val list = repo.getEmpList()
            list[0].biography = null.toString()
            repo.setEmpList(list)
            viewModel = EmployeeListViewModel(repo)

            //Act & Assert
            assertThat(viewModel.getEmpList().getOrAwaitValueTest().size).isEqualTo(1)
            assertThat(viewModel.getIsEmpty().getOrAwaitValueTest()).isEqualTo(false)
            assertThat(viewModel.getIsRefreshing().getOrAwaitValueTest()).isEqualTo(false)
        }
    }

    @Test
    fun `rainy path - retrieve list with null photo large`(){
        testCoroutineRule.runBlockingTest {
            //Arrange
            val repo = FakeEmployeeRepository()
            val list = repo.getEmpList()
            list[0].photoLarge = null.toString()
            repo.setEmpList(list)
            viewModel = EmployeeListViewModel(repo)

            //Act & Assert
            assertThat(viewModel.getEmpList().getOrAwaitValueTest().size).isEqualTo(1)
            assertThat(viewModel.getIsEmpty().getOrAwaitValueTest()).isEqualTo(false)
            assertThat(viewModel.getIsRefreshing().getOrAwaitValueTest()).isEqualTo(false)
        }
    }

    @Test
    fun `rainy path - retrieve list with null photo small`(){
        testCoroutineRule.runBlockingTest {
            //Arrange
            val repo = FakeEmployeeRepository()
            val list = repo.getEmpList()
            list[0].photoSmall = null.toString()
            repo.setEmpList(list)
            viewModel = EmployeeListViewModel(repo)

            //Act & Assert
            assertThat(viewModel.getEmpList().getOrAwaitValueTest().size).isEqualTo(1)
            assertThat(viewModel.getIsEmpty().getOrAwaitValueTest()).isEqualTo(false)
            assertThat(viewModel.getIsRefreshing().getOrAwaitValueTest()).isEqualTo(false)
        }
    }
}