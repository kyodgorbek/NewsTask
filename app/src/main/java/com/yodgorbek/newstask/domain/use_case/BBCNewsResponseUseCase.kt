package com.yodgorbek.newstask.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.yodgorbek.newstask.domain.repository.NewsRepository

class BBCNewsResponseUseCase(
    private val newsRepository: NewsRepository
) {

     suspend operator fun invoke() = newsRepository.getNews()
}