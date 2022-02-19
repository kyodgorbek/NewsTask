package com.yodgorbek.newstask.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment


import com.yodgorbek.newstask.R
import com.yodgorbek.newstask.databinding.BbcnewsFragmentBinding


import com.yodgorbek.newstask.model.Article
import com.yodgorbek.newstask.presentation.adapter.NewsAdapter
import com.yodgorbek.newstask.presentation.viewmodel.BBCNewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel



class BBCNewsFragment : Fragment(R.layout.bbcnews_fragment) {
    private val viewModel by viewModel<BBCNewsViewModel>()
    private var adapter: NewsAdapter?= null

    private var _binding: BbcnewsFragmentBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!


    // with the backing property of the kotlin we extract
    // the non null value of the _binding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate the layout and bind to the _binding
        _binding = BbcnewsFragmentBinding.inflate(inflater, container, false)
        //Add adapter in recyclerView
        binding.recyclerView.adapter = adapter

        // listening new data
        viewModel.news.observe(viewLifecycleOwner) { response ->
            adapter?.differ?.submitList(response.articles as MutableList<Article>)
        }

        viewModel.progress.observe(viewLifecycleOwner){  showLoading ->
            binding.progressBar.isVisible = showLoading
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }

