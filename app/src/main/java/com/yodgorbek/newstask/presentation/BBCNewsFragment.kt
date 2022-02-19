package com.yodgorbek.newstask.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yodgorbek.newstask.R
import com.yodgorbek.newstask.databinding.BbcnewsFragmentBinding
import com.yodgorbek.newstask.domain.utils.parseDate

import com.yodgorbek.newstask.model.Article
import com.yodgorbek.newstask.presentation.adapter.NewsAdapter
import com.yodgorbek.newstask.presentation.viewmodel.BBCNewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Comparator


class BBCNewsFragment : BaseFragment<BbcnewsFragmentBinding>() {
    override val layoutId: Int = R.layout.bbcnews_fragment
    private val viewModel by viewModel<BBCNewsViewModel>()
    private val adapter = NewsAdapter() { article ->
        findNavController().navigate(
            BBCNewsFragmentDirections.actionBbcnewsFragmentToDetailFragment(article)
        )
    }



    // with the backing property of the kotlin we extract
    // the non null value of the _binding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Add adapter in recyclerView
        binding.recyclerView.adapter = adapter

        // listening new data
        viewModel.news.observe(viewLifecycleOwner) { response ->

            adapter.differ.submitList(response.articles as MutableList<Article>)
        }

        viewModel.progress.observe(viewLifecycleOwner){  showLoading ->
            binding.progressBar.isVisible = showLoading
        }

    }
    }

