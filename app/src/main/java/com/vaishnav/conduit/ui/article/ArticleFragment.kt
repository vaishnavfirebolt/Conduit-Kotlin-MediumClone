package com.vaishnav.conduit.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vaishnav.conduit.R
import com.vaishnav.conduit.databinding.FragmentArticleBinding
import com.vaishnav.conduit.extensions.loadImage
import com.vaishnav.conduit.extensions.timeStamp

class ArticleFragment : Fragment() {

    private var _articleId : String? = null

    private var _binding : FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let{
            _articleId = it.getString(resources.getString(R.string.arg_article_id))
        }

        _articleId?.let { articleViewModel.fetchArticle(it) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleViewModel.article.observe({lifecycle}){
            binding.apply {
                titleTextView.text = it.title
                bodyTextView.text = it.body
                authorTextView.text = it.author.username
                dateTextView.timeStamp = it.createdAt
                avatarImageView.loadImage(it.author.image , true)
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}