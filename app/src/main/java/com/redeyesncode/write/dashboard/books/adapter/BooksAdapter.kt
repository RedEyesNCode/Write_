package com.redeyesncode.write.dashboard.books.adapter

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redeyesncode.write.dashboard.books.model.BooksResponseModel
import com.redeyesncode.write.databinding.BookItemBinding

class BooksAdapter(var context: Context,var dataItems:ArrayList<BooksResponseModel>):RecyclerView.Adapter<BooksAdapter.MyViewHolder> (){

    lateinit var binding: BookItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = BookItemBinding.inflate(LayoutInflater.from(context))

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Apply logics accordingly the data received from the api, also make theui accordingly.


    }

    override fun getItemCount(): Int {

        return dataItems.size
    }

    open class MyViewHolder(var binding: BookItemBinding):RecyclerView.ViewHolder(binding.root)

    interface onBookAdapterClick{

        fun onBookClicked(int:Position)

    }
}