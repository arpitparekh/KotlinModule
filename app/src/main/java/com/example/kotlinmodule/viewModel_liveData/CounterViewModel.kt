package com.example.kotlinmodule.viewModel_liveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CounterViewModel : ViewModel() {

    private var number=0
    private var countLiveData=MutableLiveData<Int>()

    fun getInitialCount():MutableLiveData<Int>{
        countLiveData.value=number
        return countLiveData
    }

    fun getCurrentCount():Int{
        number+=1

        countLiveData.value=number

        return countLiveData.value!!

    }
}