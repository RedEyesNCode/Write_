package com.redeyesncode.write.dashboard.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.redeyesncode.write.R
import com.redeyesncode.write.base.BaseActivity
import com.redeyesncode.write.dashboard.books.viewmodel.BookViewModel
import com.redeyesncode.write.databinding.ActivityBooksBinding

class BooksActivity : BaseActivity() {

    lateinit var binding: ActivityBooksBinding
    lateinit var bookViewModel: BookViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        attachObservers()
        initialApiCall()
    }

    private fun setupViewModel() {
        bookViewModel = BookViewModel()
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        binding.viewmodel = bookViewModel
    }

    private fun initialApiCall() {
        bookViewModel.getAllBooks()
    }

    private fun attachObservers() {

        bookViewModel.isFailed.observe((this)){

            if(it!=null){
                showDialogImportantAlert(it)
            }
        }
        bookViewModel.isSuccess.observe((this)){
            if(it){
                showLoader()
            }else{
                hideLoader()
            }
        }
        bookViewModel.booksResponse.observe((this)){


            // Code for setting up the adapter.

        }

    }
}