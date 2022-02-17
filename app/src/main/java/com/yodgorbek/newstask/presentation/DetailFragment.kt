package com.yodgorbek.newstask.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.yodgorbek.newstask.R
import com.yodgorbek.newstask.databinding.DetailFragmentBinding
import com.yodgorbek.newstask.presentation.viewmodel.DetailNewsViewModel
import com.squareup.picasso.Picasso
import com.yodgorbek.newstask.domain.utils.parseDate
import com.yodgorbek.newstask.domain.utils.userFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.detail_fragment) {
    private var _binding: DetailFragmentBinding? = null
    private val viewModel by viewModel<DetailNewsViewModel>()

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailFragmentBinding.inflate(layoutInflater)

        args.article
        initObservers()
    }

    private fun initObservers() {
        viewModel.news.observe(viewLifecycleOwner) { newsDetails ->
            binding.title.text = newsDetails.articles.firstOrNull()?.title


            newsDetails.articles.firstOrNull()?.publishedAt?.parseDate()?.let {
                binding.articleDates.text = it.userFormat()
            }
            Picasso.get().load(newsDetails.articles.firstOrNull()?.urlToImage)
                .into(binding.imageUrl)


        }
    }
}




