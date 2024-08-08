package com.redeyesncode.write.dashboard.books.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redeyesncode.write.base.CommonResponseModel
import com.redeyesncode.write.dashboard.books.model.BooksResponseModel
import com.redeyesncode.write.dashboard.books.repo.BookRepo
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel() :ViewModel(){

    private val _commonResponse = MutableLiveData<CommonResponseModel>()
    val commonResponse: LiveData<CommonResponseModel> = _commonResponse

    private val _booksResponse = MutableLiveData<BooksResponseModel>()
    val booksResponse: LiveData<BooksResponseModel> = _booksResponse


    //Global observers for showing loaders and error messages from the api.
    private val _isFailed = MutableLiveData<String>()
    val isFailed :LiveData<String> = _isFailed
    private val _isLoading = MutableLiveData<Boolean>()
    val isSuccess :LiveData<Boolean> = _isLoading

    var bookRepo:BookRepo = BookRepo()


//    fun getAllBooks() = viewModelScope.launch {
//        getAllBooksCoroutine()
//
//    }

//    private suspend fun getAllBooksCoroutine() {
//
//        try {
//            val response = bookRepo.getAllBooksCoroutine()
//            _isLoading.value = true
//            response.enqueue(object : Callback<BooksResponseModel> {
//
//                override fun onResponse(
//                    call: Call<BooksResponseModel>,
//                    response: Response<BooksResponseModel>
//                ) {
//                    _isLoading.value = false
//                    if(response.code()==200){
//                        _booksResponse.postValue(response.body())
//                    }else{
//                        _isFailed.value = "Oops Something Went Wrong.Please try again."
//                    }
//                }
//                override fun onFailure(call: Call<BooksResponseModel>, t: Throwable) {
//                    _isFailed.value = t.message
//                }
//            })
//        }catch (t:Throwable){
//
//            when(t){
//                is IOException->{
//                    _isFailed.value = "IO Exception"
//                }
//                else -> {
//                    _isFailed.value = "Exception."+t.message
//
//                    Log.i("RETROFIT",t.message!!)
//                }
//
//
//
//            }
//
//
//        }
//
//    }

}