package com.yodgorbek.newstask

import com.yodgorbek.newstask.data.internet.NewsInterface

import com.yodgorbek.newstask.model.NewsResponse


class MockNewsInterface : NewsInterface {


    override suspend fun getNews(): NewsResponse {
        return NewsResponse(
            listOf(),
            "status",
            10
        )// Pass dummy implementation
    }
}