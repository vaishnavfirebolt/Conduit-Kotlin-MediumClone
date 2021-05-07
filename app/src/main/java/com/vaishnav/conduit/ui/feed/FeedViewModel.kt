package com.vaishnav.conduit.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaishnav.api.models.entities.Article
import com.vaishnav.conduit.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val _feed = MutableLiveData<List<Article>>()
    val feed : LiveData<List<Article>> = _feed

    fun fetchGlobalFeed() = viewModelScope.launch {
        ArticlesRepo.getGlobalFeed()?.let {
            _feed.postValue(it)
        }
    }

    fun fetchMyFeed() = viewModelScope.launch {
        ArticlesRepo.getMyFeed()?.let{
            _feed.postValue(it)
        }
    }

}