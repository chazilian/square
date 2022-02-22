package com.foobear.square

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foobear.square.ui.emplist.EmployeeListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fg = EmployeeListFragment()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, fg)
                    .commit()
        }
    }
}