package com.yodgorbek.newstask.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yodgorbek.newstask.domain.use_case.DetailNewsUseCase
import com.yodgorbek.newstask.domain.utils.fold
import com.yodgorbek.newstask.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
class DetailNewsViewModel (private val useCase: DetailNewsUseCase) : ViewModel() {


    var news= MutableLiveData<NewsResponse>()
    // Expose to the outside world

    // Expose to the outside world
    val error = MutableLiveData<String>()
    var progress = MutableLiveData(false)

    init{
        getDetailNews()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDetailNews() {
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