package com.redeyesncode.write.dashboard.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redeyesncode.write.R
import com.redeyesncode.write.dashboard.books.viewmodel.BookViewModel
import com.redeyesncode.write.databinding.ActivityBooksBinding

class BooksActivity : AppCompatActivity() {

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

    }

    private fun initialApiCall() {

    }

    private fun attachObservers() {

    }
}