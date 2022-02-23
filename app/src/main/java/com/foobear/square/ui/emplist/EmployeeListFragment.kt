package com.foobear.square.ui.emplist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.foobear.square.R
import com.foobear.square.data.entity.responses.Employee
import com.foobear.square.databinding.EmployeeListFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class EmployeeListFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: EmployeeListViewModelFactory by instance()

    private lateinit var viewModel: EmployeeListViewModel

    private lateinit var binding: EmployeeListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EmployeeListViewModel::class.java)
        return inflater.inflate(R.layout.employee_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EmployeeListFragmentBinding.bind(view)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.rvEmpList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvEmpList.itemAnimator = DefaultItemAnimator()
        val adapter = EmployeeListAdapter(arrayListOf<Employee>())
        binding.rvEmpList.adapter = adapter
        viewModel.getEmpList().observe(viewLifecycleOwner, Observer { result ->
            if(!result.isNullOrEmpty()) {
                adapter.setList(result.sortedBy { it.team })
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.getIsEmpty().observe(viewLifecycleOwner, Observer { result ->
            binding.tvEmptyList.visibility = if (result){View.VISIBLE} else{ View.GONE}
            binding.rvEmpList.visibility = if (!result){View.VISIBLE} else{ View.INVISIBLE}
        })

        viewModel.getIsRefreshing().observe(viewLifecycleOwner, Observer { result ->
            binding.pbEmployeeList.visibility = if (result){View.VISIBLE} else{ View.GONE}
        })

        binding.srlContainer.setOnRefreshListener {
            binding.tvEmptyList.visibility = View.GONE
            binding.rvEmpList.visibility = View.INVISIBLE
            viewModel.refreshEmployeeList()
            binding.srlContainer.isRefreshing = false
        }
    }
}