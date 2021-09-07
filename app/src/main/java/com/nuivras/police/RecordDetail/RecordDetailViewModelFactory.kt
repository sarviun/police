package com.nuivras.police.RecordDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nuivras.police.StreetLevelCrime

class RecordDetailViewModelFactory(private val crime: StreetLevelCrime,
                                   private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordDetailViewModel::class.java)) {
            return RecordDetailViewModel(crime, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}