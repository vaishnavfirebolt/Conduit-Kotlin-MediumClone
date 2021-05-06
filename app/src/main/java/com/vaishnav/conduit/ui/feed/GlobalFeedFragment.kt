package com.vaishnav.conduit.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaishnav.conduit.databinding.FragmentFeedBinding

class GlobalFeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel

    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(FeedViewModel::class.java)
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val root = binding.root

        val articleFeedAdapter = ArticleFeedAdapter()
        binding.feedRecyclerView.apply {
            adapter = articleFeedAdapter
            layoutManager = LinearLayoutManager(root.context)
        }
        viewModel.feed.observe({lifecycle}){
            articleFeedAdapter.submitList(it)
        }

        viewModel.fetchGlobalFeed()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}