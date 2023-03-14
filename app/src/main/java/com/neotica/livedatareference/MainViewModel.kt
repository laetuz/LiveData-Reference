package com.neotica.livedatareference

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

//Step 5: Extends to ViewModel
class MainViewModel : ViewModel(){
    //Step 6: add companion object
    companion object{
        //Step 6.1: Set the value for one second
        private const val ONE_SECOND = 1000
    }
    //Step 7: Initialize SystemClock -> elapsedRealTime
    private val mInitialTime = SystemClock.elapsedRealtime()
    //Step 7.1: Enable MutableLiveData
    private val mElapsedTime = MutableLiveData<Long?>()

    //Step 8: Initialize timer
    init {
        //Step 8.1: Creates a new Timer() variable
        val timer = Timer()
        //Step 9: Implement members
        timer.scheduleAtFixedRate(object  : TimerTask(){
            //Step 10: override run
            override fun run() {
                //Step 11: Set the value of system clock -> see line 35
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
            //Step 12: Schedule the time arguments
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    //Step 13:
    fun getElapsedTime(): LiveData<Long?> {
        return mElapsedTime
    }
}