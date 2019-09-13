package com.synergy.android.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class AddCarViewModel : ViewModel() {

    val number = MutableLiveData<String>()
    val enableDone = Transformations.map<String, Boolean>(number) { input -> input.isNotEmpty() }

}