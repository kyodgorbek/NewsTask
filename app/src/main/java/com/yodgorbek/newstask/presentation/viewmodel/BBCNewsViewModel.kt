package com.yodgorbek.newstask.presentation.viewmodel


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yodgorbek.newstask.model.NewsResponse
import com.yodgorbek.newstask.domain.use_case.BBCNewsResponseUseCase
import com.yodgorbek.newstask.domain.utils.fold

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
class BBCNewsViewModel(private val useCase: BBCNewsResponseUseCase) : ViewModel() {


     var news= MutableLiveData<NewsResponse>()
    // Expose to the outside world

    // Expose to the outside world
    private val error = MutableLiveData<String>()
    var progress = MutableLiveData(false)

init{
    getNews()
}
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
  progress.postValue(true)
            useCase.invoke()
                .fold({ newsResponse ->
                    news.postValue(newsResponse)
                }, {
                    error.postValue(it.message)
                })
            progress.postValue(false)
        }
    }




}