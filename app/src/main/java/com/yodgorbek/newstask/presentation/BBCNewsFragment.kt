package com.yodgorbek.newstask.presentation


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.yodgorbek.newstask.databinding.BbcnewsFragmentBinding
import com.yodgorbek.newstask.model.Article
import com.yodgorbek.newstask.presentation.adapter.NewsAdapter
import com.yodgorbek.newstask.presentation.viewmodel.BBCNewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BBCNewsFragment : Fragment() {


    private var _binding: BbcnewsFragmentBinding? = null
    private val binding: BbcnewsFragmentBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")

    private val viewModel by viewModel<BBCNewsViewModel>()
    private val adapter = NewsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = BbcnewsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {


        viewModel.news.observe(viewLifecycleOwner) { response ->


            adapter.differ.submitList(response.articles as MutableList<Article>)
        }

        viewModel.progress.observe(viewLifecycleOwner) { showLoading ->
            binding.progressBar.isVisible = showLoading
        }
    }

}

