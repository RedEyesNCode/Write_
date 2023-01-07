package com.redeyesncode.write.dashboard.books.adapter

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redeyesncode.write.R
import com.redeyesncode.write.dashboard.books.model.BooksResponseModel
import com.redeyesncode.write.databinding.BookItemBinding

class BooksAdapter(var context: Context,var dataItems:ArrayList<BooksResponseModel.Data>,var onClick: onBookAdapterClick):RecyclerView.Adapter<BooksAdapter.MyViewHolder> (){

    lateinit var binding: BookItemBinding
    var onReadClick:onBookAdapterClick = onClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = BookItemBinding.inflate(LayoutInflater.from(context))

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Apply logics accordingly the data received from the api, also make theui accordingly.
        var data = dataItems.get(position)
        holder.binding.tvBookName.text = data.bookName
        holder.binding.tvBookSizeCategory.text = "Size : ${data.bookSize} • Category : ${data.categoryName}"
        Glide.with(context).load(data.bookCoverImage).placeholder(R.drawable.book).into(holder.binding.ivBookImage)

        holder.binding.btnReadNow.setOnClickListener {
            onReadClick.onBookClicked(position)
        }
    }

    override fun getItemCount(): Int {

        return dataItems.size
    }

    open class MyViewHolder(var binding: BookItemBinding):RecyclerView.ViewHolder(binding.root)

    interface onBookAdapterClick{

        fun onBookClicked(position: Int)

    }
}