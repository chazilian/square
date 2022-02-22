package com.foobear.square.ui.emplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foobear.square.R
import com.foobear.square.data.entity.Employee

class EmployeeListAdapter(var employees: ArrayList<Employee>): RecyclerView.Adapter<EmployeeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_holder, parent, false)
        return EmployeeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        val current = employees[position]
        Glide.with(holder.itemView)
            .load(current.photoSmall)
            .placeholder(R.drawable.no_image_found)
            .into(holder.picture)
        holder.name.text ="Name: " +  current.fullName
        holder.team.text ="Team: " + current.team
        holder.empType.text ="E-Type: " + current.employeeType.value

    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun setList(list: List<Employee>){
        employees.clear()
        employees.addAll(list)
    }
}

class EmployeeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val picture: ImageView = itemView.findViewById(R.id.iv_emp_image)
    val name: TextView = itemView.findViewById(R.id.tv_fullname)
    val team: TextView = itemView.findViewById(R.id.tv_team)
    val empType: TextView = itemView.findViewById(R.id.tv_empType)

}