package com.yodgorbek.newstask.data.internet

import com.yodgorbek.newstask.model.NewsResponse
import com.yodgorbek.newstask.domain.utils.Constants
import retrofit2.http.GET

interface NewsInterface {

    @GET(Constants.BBC_URL)
    suspend fun getNews(): NewsResponse
    @GET(Constants.BBC_URL)
    suspend fun getDetailNews(): NewsResponse

}