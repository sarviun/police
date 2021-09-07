package com.nuivras.police.RecordDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nuivras.police.StreetLevelCrime

class RecordDetailViewModel (crime: StreetLevelCrime, app: Application): AndroidViewModel(app) {

    private val _crime = MutableLiveData<StreetLevelCrime>()
    val crime: LiveData<StreetLevelCrime>
        get() = _crime

    init {
        _crime.value = crime
    }
}