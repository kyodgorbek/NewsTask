package com.yodgorbek.newstask.domain.repository


import com.yodgorbek.newstask.MockNewsInterface
import com.yodgorbek.newstask.model.NewsResponse
import com.yodgorbek.newstask.model.Source
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class NewsRepositoryTest {


    private var mockNewsInterface = MockNewsInterface()
    private val newsResponse = NewsResponse(listOf(), "status", 10)
    private val secondResponse = NewsResponse(listOf(), "status", 10)


    @Test
    fun `checking articleSize`() = runBlocking {

        val articleSize = mockNewsInterface.getNews().articles.size
        val responseSize = newsResponse.articles.size
        assert(articleSize == responseSize)

    }

    @Test
    fun `compare twoResponse`() {

        assertEquals(newsResponse, secondResponse)

    }


    @Test
    fun `check articleEquals`() {
        assert(newsResponse.articles.equals(secondResponse.articles))

    }


    @Test
    fun `check Source`() {
        val status = Source("bbc-news", "BBC-News")
        val statusId = "bbc-news"
        val statusName = "BBC-News"
        assert(statusId == status.id)
        assert(statusName.equals(status.name))
    }


}








