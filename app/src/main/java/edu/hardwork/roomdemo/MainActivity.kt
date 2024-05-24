package edu.hardwork.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.hardwork.roomdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val employeeDao = (application as EmployeeApp).db.employeeDao()
        binding?.btnAdd?.setOnClickListener{
            //TODO call addreard with employee
            addRecord(employeeDao)
        }
    }

    fun addRecord(employeeDao: EmployeeDao){
        val name = binding?.etName?.text.toString()
        val email = binding?.etEmailId?.text.toString()

        if(email.isNotEmpty()&&name.isNotEmpty()){
            lifecycleScope.launch {
                employeeDao.insert(EmployeeEntity(name=name, email = email))
                Toast.makeText(this@MainActivity,"record saved",Toast.LENGTH_LONG).show()
                binding?.etName?.text?.clear()
                binding?.etEmailId?.text?.clear()
            }
        }else{
            Toast.makeText(this@MainActivity,"Name or Email can not be black",Toast.LENGTH_LONG).show()
        }
    }
}