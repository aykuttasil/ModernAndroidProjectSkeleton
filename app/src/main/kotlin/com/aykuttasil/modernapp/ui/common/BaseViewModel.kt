package com.aykuttasil.modernapp.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aykuttasil.modernapp.App
import com.aykuttasil.modernapp.util.RxAwareViewModel
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel(app: App) : RxAwareViewModel(app) {
  internal fun <T> launchOnViewModelScope(block: suspend () -> LiveData<T>): LiveData<T> {
    return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
      emitSource(block())
    }
  }
}
