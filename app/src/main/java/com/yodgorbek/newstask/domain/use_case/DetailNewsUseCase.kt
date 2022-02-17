package com.yodgorbek.newstask.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.yodgorbek.newstask.domain.repository.NewsRepository

class DetailNewsUseCase(
    private val newsRepository: NewsRepository
) {
    @RequiresApi(Build.VERSION_CODES.N)
    suspend operator fun invoke() = newsRepository.getDetailNews()
}