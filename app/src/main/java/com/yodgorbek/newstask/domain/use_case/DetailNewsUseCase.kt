package com.yodgorbek.newstask.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.yodgorbek.newstask.domain.repository.NewsRepository

class DetailNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke() = newsRepository.getDetailNews()
}