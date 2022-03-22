package com.foobear.square.ui.empdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.foobear.square.R
import com.foobear.square.databinding.EmployeeDetailsFragmentBinding
import com.foobear.square.ui.emplist.EmployeeListViewModel
import com.foobear.square.ui.emplist.EmployeeListViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class EmployeeDetailsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var viewModel: EmployeeDetailsViewModel

    private lateinit var binding: EmployeeDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val empId = arguments?.getString("empId").orEmpty()
        val viewModelFactory: EmployeeDetailsViewModelFactory by kodein.newInstance{EmployeeDetailsViewModelFactory(instance(), empId)}
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EmployeeDetailsViewModel::class.java)
        return inflater.inflate(R.layout.employee_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmployeeDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EmployeeDetailsFragmentBinding.bind(view)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewModel.employee.observe(viewLifecycleOwner, Observer {
            binding.ivEmpName.text = it.fullName
        })
    }

}