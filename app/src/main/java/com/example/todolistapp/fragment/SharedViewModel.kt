package com.example.todolistapp.fragment

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todolistapp.R
import com.example.todolistapp.data.models.Priority
import com.example.todolistapp.data.models.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {

    /** ==================== List Fragment ====================== **/
    // This is to adjust the visibility of the icons in the recyclerView
    val emptyDatabase: MutableLiveData<Boolean> =  MutableLiveData(false)

    fun checkDatabaseEmpty(toDoData: List<ToDoData>){
        emptyDatabase.value = toDoData.isEmpty()
    }
    //Below function will check if the fields are empty or not
     fun verifyDataFromUser(title: String, description: String): Boolean{

        // alternatively we can say return !(title.isEmpty() || description.isEmpty())

        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())
    }

    /** ==================== Add/Update Fragment ====================== **/
    // Below function to change the color of the selected item in the spinner
    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {

            when (position){
                0 -> {(parent?.getChildAt(0)as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> {(parent?.getChildAt(0)as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> {(parent?.getChildAt(0)as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
    }

     fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}

            else -> Priority.LOW
        }
    }
}