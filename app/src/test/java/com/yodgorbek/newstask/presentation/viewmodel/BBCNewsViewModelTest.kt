package com.yodgorbek.newstask.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.yodgorbek.newstask.MainCoroutineRule
import com.yodgorbek.newstask.MockNewsInterface

import com.yodgorbek.newstask.data.internet.NewsInterface
import com.yodgorbek.newstask.domain.repository.NewsRepository
import com.yodgorbek.newstask.domain.use_case.BBCNewsResponseUseCase
import com.yodgorbek.newstask.model.NewsResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class BBCNewsViewModelTest {
    private lateinit var apiInterface: NewsInterface
    private val news = MutableLiveData<NewsResponse>()

    private lateinit var newsRepository: NewsRepository
    private lateinit var bbcNewsResponseUseCase: BBCNewsResponseUseCase
    private lateinit var viewModel: BBCNewsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        apiInterface = MockNewsInterface()
        newsRepository = NewsRepository(apiInterface)
        bbcNewsResponseUseCase = BBCNewsResponseUseCase(newsRepository)
        viewModel = BBCNewsViewModel(bbcNewsResponseUseCase)
    }


    @Test
    fun `check Value`() {
        assert(news.value == viewModel.news.value)

    }

    @Test
    fun `check newsSize`() {
        val size = news.value?.articles?.size
        val viewModelSize = viewModel.news.value?.articles?.size
        assertEquals(size, viewModelSize)
    }


}