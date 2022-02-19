package com.yodgorbek.newstask.domain.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.yodgorbek.newstask.data.internet.NewsInterface
import com.yodgorbek.newstask.model.NewsResponse
import com.yodgorbek.newstask.domain.utils.Result
import com.yodgorbek.newstask.domain.utils.parseDate
import java.util.Comparator


class NewsRepository(
   private val apiInterface:NewsInterface
){


@RequiresApi(Build.VERSION_CODES.N)
suspend fun getNews() : Result<NewsResponse>{
   return try {
       val response = apiInterface.getNews()
      Result.Success(response)
   } catch (ex: Exception) {
      Result.Error(ex)
   }
}



}



