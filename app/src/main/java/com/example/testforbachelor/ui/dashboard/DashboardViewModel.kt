package com.example.testforbachelor.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    fun genNum(): Int{
        return (1..3).random()
    }
    private val _text = MutableLiveData<String>().apply {
        val random = genNum()
        when(random){
            1 -> value = "Testcase 1"
            2 -> value = "Testcase 2"
            3 -> value = "Testcase 3"
        }

        //value = "This is dashboard Fragment"

    }
    val text: LiveData<String> = _text
}