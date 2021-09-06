package com.nuivras.police

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


class RecordListViewModel : ViewModel() {

    enum class PoliceApiStatus { LOADING, ERROR, DONE}

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<PoliceApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<PoliceApiStatus>
        get() = _status

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // Internally, we use a MutableLiveData, because we will be updating the List
    private val _properties = MutableLiveData<List<Any>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<Any>>
        get() = _properties


    fun getStreetLevelCrimes(latitude: String, longitude: String) {
        coroutineScope.launch {

            try {
                _status.value = PoliceApiStatus.LOADING
                _properties.value = PoliceServiceAPI.retrofitService
                    .getAllStreetLevelCrimes(latitude, longitude)
                _status.value = PoliceApiStatus.DONE
            } catch (e: Exception) {
                _status.value = PoliceApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}