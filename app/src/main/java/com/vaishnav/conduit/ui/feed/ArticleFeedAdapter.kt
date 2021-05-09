package com.vaishnav.conduit.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaishnav.api.models.entities.Article
import com.vaishnav.conduit.databinding.ListItemArticleBinding
import com.vaishnav.conduit.extensions.loadImage
import com.vaishnav.conduit.extensions.timeStamp

class ArticleFeedAdapter(val onArticleClicked: (slug: String) -> Unit) :
    ListAdapter<Article, ArticleFeedAdapter.ArticleFeedViewHolder>(ArticleComparator()) {

    inner class ArticleFeedViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    class ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem.toString() == newItem.toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleFeedViewHolder {
        val binding =
            ListItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleFeedViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ArticleFeedViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorTextView.text = article.author.username
            titleTextView.text = article.title
            bodySnippetTextView.text = article.body
            dateTextView.timeStamp = article.createdAt
            avatarImageView.loadImage(article.author.image, true)

            root.setOnClickListener { onArticleClicked(article.slug) }
        }

    }
}