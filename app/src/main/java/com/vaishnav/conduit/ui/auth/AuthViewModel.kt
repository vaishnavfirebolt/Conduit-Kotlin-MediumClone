package com.vaishnav.conduit.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaishnav.api.models.entities.User
import com.vaishnav.conduit.data.UserRepo
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user : LiveData<User?> = _user

    fun login(email : String , password : String) =
        viewModelScope.launch {
            UserRepo.loginUser(email, password)?.let {
                _user.postValue(it)
            }
        }
    fun signUp(email : String , password : String , username : String) =
        viewModelScope.launch {
            UserRepo.signUpUser(email, password , username)?.let {
                _user.postValue(it)
            }
        }

}